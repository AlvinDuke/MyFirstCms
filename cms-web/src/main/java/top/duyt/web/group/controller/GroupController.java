package top.duyt.web.group.controller;

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

import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Group;
import top.duyt.model.User;
import top.duyt.service.ICategoryService;
import top.duyt.service.IGroupService;
import top.duyt.service.IUserService;
import top.duyt.utils.JSONUtil;
import top.duyt.web.user.dto.UserDto;
import top.duyt.dto.Page;

@Controller
@RequestMapping("/group")
public class GroupController {

	private IUserService userService;
	private IGroupService groupService;
	private ICategoryService categoryService;

	/**
	 * 组列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/groups")
	public String groups(Model model) {
		Page<Group> groups = groupService.findGroups();
		model.addAttribute("groups", groups);
		return "group/groupList";
	}

	/**
	 * 新增跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("g", new UserDto());
		return "group/groupAddInput";
	}

	/**
	 * 组新增
	 * 
	 * @param g
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated Group g, BindingResult br) {
		if (br.hasErrors()) {
			return "group/groupAddInput";
		}
		groupService.addGroup(g);
		return "redirect:/group/groups";
	}

	/**
	 * 更新跳转
	 * 
	 * @param model
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/update/{gid}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable Integer gid) {
		Group g = groupService.load(gid);
		model.addAttribute("g", g);
		return "group/groupUpdateInput";
	}

	/**
	 * 更新组
	 * 
	 * @param ud
	 * @return
	 */
	@RequestMapping(value = "/update/*", method = RequestMethod.POST)
	public String update(@ModelAttribute Group g) {

		int id = g.getId();
		Group gDb = groupService.load(id);
		if (gDb != null) {
			gDb.setName(g.getName());
			gDb.setDescri(g.getDescri());
			groupService.update(gDb);
		}
		return "redirect:/group/groups";
	}

	/**
	 * 删除组
	 * 
	 * @param gid
	 * @return
	 */
	@RequestMapping(value = "/delete/{gid}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer gid) {
		groupService.deleteGroup(gid);
		return "redirect:/group/groups";
	}

	/**
	 * 查看用户
	 * 
	 * @param gid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{gid}", method = RequestMethod.GET)
	public String show(@PathVariable Integer gid, Model model) {
		Group g = groupService.load(gid);
		if (g != null) {
			model.addAttribute("g", g);
			List<User> us = userService.ListGroupByGid(gid);
			model.addAttribute("us", us);
		}
		return "group/groupShow";
	}

	/**
	 * 查看组管理的栏目
	 * @param gId
	 * @param model
	 * @return
	 */
	@RequestMapping("/showCategories/{gId}")
	public String listGroupCates(@PathVariable Integer gId, Model model) {
		//初始化栏目树，全部栏目
		List<CategoryTreeDto> cts = categoryService.listAllCateTreeDto();
		// 获取当前栏目
		List<CategoryTreeDto> ctsSeld = groupService.generateCateTreeByGid(gId);
		
		//存在管理的栏目，则先选中根节点
		/*if (ctsSeld.size()>0) {
			for (CategoryTreeDto ctd : cts) {
				if (ctd.getId() == 9999) {
					ctd.setChecked(true);
				}
			}
		}*/
		
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
		return "group/groupCategories";
	}
	
	/**
	 * 编辑组管理的栏目
	 * @param gId
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateGroupCates/{gId}")
	public String updateGroupCates(@PathVariable Integer gId, Model model) {
		//初始化栏目树，全部栏目
		List<CategoryTreeDto> cts = categoryService.listAllCateTreeDto();
		// 获取当前已选栏目
		List<CategoryTreeDto> ctsSeld = groupService.generateCateTreeByGid(gId);
		
		//存在管理的栏目，则先选中根节点
		/*if (ctsSeld.size()>0) {
			for (CategoryTreeDto ctd : cts) {
				if (ctd.getId() == 9999) {
					ctd.setChecked(true);
				}
			}
		}*/
		
		//已选的节点要选中
		for (CategoryTreeDto ctd : cts) {
			for (CategoryTreeDto ctdSeld : ctsSeld) {
				//判断当前节点是否要选中
				if(ctdSeld.getId() == ctd.getId()){
					ctd.setChecked(true);
				}
			}
		}
		model.addAttribute("treeData",JSONUtil.bean2JSON(cts));
		model.addAttribute("groupId", gId);
		return "group/updateGroupCategories";
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

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
