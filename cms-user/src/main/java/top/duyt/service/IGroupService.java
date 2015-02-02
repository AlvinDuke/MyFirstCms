package top.duyt.service;

import java.util.List;

import top.duyt.model.Group;

import top.duyt.dto.Page;

public interface IGroupService {

	/**
	 * 添加一个新组
	 * @param group
	 */
	public void addGroup(Group group);
	
	/**
	 * 删除一个组
	 * @param gid
	 */
	public void deleteGroup(int gid);
	
	/**
	 * 查询一个组对象
	 * @param gid
	 * @return
	 */
	public Group load(int gid);
	
	/**
	 * 更新一个组
	 * @param g
	 */
	public void update(Group g);
	
	/**
	 * 列表出所有的组
	 */
	public List<Group> listAllGroups();
	
	/**
	 * 获取用户组分页列表
	 * @return
	 */
	public Page<Group> findGroups();
	
}
