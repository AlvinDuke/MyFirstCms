package top.duyt.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.model.Emur.RoleType;
import top.duyt.service.IGroupService;
import top.duyt.service.IRoleService;
import top.duyt.service.IUserService;
import top.duyt.utils.EnumUtil;

import top.duyt.dto.Page;

@Controller
@RequestMapping("/role")
public class RoleController {

	private IUserService userService;
	private IGroupService groupService;
	private IRoleService roleService;

	/**
	 * 角色列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/roles")
	public String roles(Model model) {
		Page<Role> roles = roleService.findRoles();
		model.addAttribute("roles", roles);
		return "role/roleList";
	}

	/**
	 * 新增跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("r",new Role());
		model.addAttribute("roleTypes",EnumUtil.enum2Name(RoleType.class));
		return "role/roleAddInput";
	}
	
	/**
	 * 角色新增
	 * @param r
	 * @param br
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated Role r,BindingResult br) {
		if (br.hasErrors()) {
			return "role/roleAddInput";
		}
		roleService.addRole(r);
		return "redirect:/role/roles";
	}
	
	/**
	 * 更新跳转
	 * @param model
	 * @param rid
	 * @return
	 */
	@RequestMapping(value = "/update/{rid}", method = RequestMethod.GET)
	public String update(Model model, 
						 @PathVariable Integer rid){
		Role r = roleService.loadRole(rid);
		model.addAttribute("r", r);
		model.addAttribute("roleTypes",EnumUtil.enum2Name(RoleType.class));
		return "role/roleUpdateInput";
	}
	
	/**
	 * 更新组
	 * @param ud
	 * @return
	 */
	@RequestMapping(value = "/update/*",method = RequestMethod.POST)
	public String update(@ModelAttribute Role r){
		
		int id = r.getId();
		Role rDb = roleService.loadRole(id);
		if (rDb!=null) {
			rDb.setRoleName(r.getRoleName());
			rDb.setRoleNum(r.getRoleNum());
			rDb.setRoleType(r.getRoleType());
			roleService.updateRole(rDb);
		}
		return "redirect:/role/roles";
	}
	
	/**
	 * 删除角色
	 * @param gid
	 * @return
	 */
	@RequestMapping(value = "/delete/{rid}",method = RequestMethod.GET)
	public String delete(@PathVariable Integer rid){
		roleService.deleteRole(rid);
		return "redirect:/role/roles";
	}
	
	/**
	 * 查看角色
	 * @param gid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{rid}",method = RequestMethod.GET)
	public String show(@PathVariable Integer rid,
						 Model model){
		Role r = roleService.loadRole(rid);
		
		if(r!=null){
			model.addAttribute("r", r);
			List<User> us = userService.ListUserByRid(rid);
			model.addAttribute("us", us);
		}
		return "role/roleShow";
	}

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IGroupService getGroupService() {
		return groupService;
	}

	@Inject
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	

}
