package top.duyt.web.user.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.service.ICategoryService;
import top.duyt.service.IGroupService;
import top.duyt.service.IRoleService;
import top.duyt.service.IUserService;
import top.duyt.utils.JSONUtil;
import top.duyt.utils.SecurityUtil;
import top.duyt.web.user.dto.UserDto;
import top.duyt.dto.Page;

@Controller
@RequestMapping("/user")
public class UserController {

	private IUserService userService;
	private IRoleService roleService;
	private IGroupService groupService;
	private ICategoryService categoryService;

	/**
	 * 用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/users")
	public String users(Model model) {
		Page<User> users = userService.findUsers();
		model.addAttribute("users", users);
		return "admin/userList";
	}

	/**
	 * 新增跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("ud",new UserDto());
		model.addAttribute("roles", roleService.listAllRoles());
		model.addAttribute("groups", groupService.listAllGroups());

		return "admin/userAddInput";
	}
	
	/**
	 * 用户新增
	 * @param userDto
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute UserDto ud) throws NoSuchAlgorithmException {
		ud.getUser().setCredate(new Date());
		ud.getUser().setPassword(SecurityUtil.getEncryptString(
				ud.getUser().getPassword(), "MD5", 16));
		userService.addUser(ud.getUser(), ud.getRoleIds(), ud.getGroupIds());
		return "redirect:/user/users";
	}
	
	/**
	 * 更新跳转
	 * @param model
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/update/{uid}", method = RequestMethod.GET)
	public String update(Model model, 
						 @PathVariable String uid){
		
		int id = Integer.valueOf(uid);
		User u = userService.loadUserByUid(id);
		if(u!=null){
			UserDto ud = new UserDto();
			ud.setUser(u);
			ud.setRoleIds(userService.listUserRoleIds(id));
			ud.setGroupIds(userService.listUserGroupIds(id));
			model.addAttribute("ud",ud);
		}
		
		model.addAttribute("roles", roleService.listAllRoles());
		model.addAttribute("groups", groupService.listAllGroups());

		return "admin/userUpdateInput";
	}
	
	/**
	 * 更新用户
	 * @param ud
	 * @return
	 */
	@RequestMapping(value = "/update/*",method = RequestMethod.POST)
	public String update(@ModelAttribute UserDto ud){
		
		int id = ud.getUser().getId();
		
		User u = userService.loadUserByUid(id);
		if (u!=null) {
			u.setNickname(ud.getUser().getNickname());
			u.setCellPhone(ud.getUser().getCellPhone());
			u.setEmail(ud.getUser().getEmail());
			u.setStatus(ud.getUser().getStatus());
			userService.updateUser(u, ud.getRoleIds(), ud.getGroupIds());
		}
		
		return "redirect:/user/users";
	}
	
	/**
	 * 切换用户状态
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/toggleStatus/{uid}",method = RequestMethod.GET)
	public String toggleStatus(@PathVariable Integer uid){
		userService.updateUserStatus(uid);
		return "redirect:/user/users";
	}
	
	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/delete/{uid}",method = RequestMethod.GET)
	public String delete(@PathVariable Integer uid){
		userService.deleteUser(uid);
		return "redirect:/user/users";
	}
	
	/**
	 * 查看用户
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{uid}",method = RequestMethod.GET)
	public String show(@PathVariable Integer uid,
						 Model model){
		
		User u = userService.loadUserByUid(uid);
		if(u!=null){
			model.addAttribute("user", u);
			
			List<Role> rs = userService.ListRolesByUid(uid);
			model.addAttribute("roles", rs);
			
			List<Group> gs = userService.ListGroupByUid(uid);
			model.addAttribute("groups", gs);
		}
		
		return "admin/userShow";
	}
	
	/**
	 * 查看组管理的栏目
	 * @param gId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showCategories/{uId}")
	public String listGroupCates(@PathVariable Integer uId, Model model) {
		//初始化栏目树，全部栏目
		List<CategoryTreeDto> cts = categoryService.listAllCateTreeDto();
		// 获取当前栏目
		List<CategoryTreeDto> ctsSeld = groupService.generateCateTreeByUid(uId);
		
		//存在管理的栏目，则先选中根节点
		if (ctsSeld.size()>0) {
			for (CategoryTreeDto ctd : cts) {
				if (ctd.getId() == 9999) {
					ctd.setChecked(true);
				}
			}
		}
		
		//已选的节点要选中
		for (CategoryTreeDto ctd : cts) {
			//节点不可用
			ctd.setChkDisabled(true);
			for (CategoryTreeDto ctdSeld : ctsSeld) {
				//判断当前节点是否要选中
				if(ctdSeld.getId() == ctd.getId()){
					ctd.setChecked(true);
				}
			}
		}
		model.addAttribute("treeData",JSONUtil.bean2JSON(cts));
		return "admin/userGroupCategories";
	}

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IGroupService getGroupService() {
		return groupService;
	}

	@Inject
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
