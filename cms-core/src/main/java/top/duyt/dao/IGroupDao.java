package top.duyt.dao;

import java.util.List;

import top.duyt.model.Group;

import top.duyt.dao.IbaseDao;
import top.duyt.dto.Page;

public interface IGroupDao extends IbaseDao<Group> {

	/**
	 * 列表出所有的组
	 * 
	 * @return
	 */
	public List<Group> listAllGroups();

	/**
	 * 获取用户组分页列表
	 * 
	 * @return
	 */
	public Page<Group> findGroups();

}
