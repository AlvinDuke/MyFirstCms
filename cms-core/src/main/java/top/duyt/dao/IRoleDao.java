package top.duyt.dao;

import java.util.List;

import top.duyt.model.Role;

import top.duyt.dao.IbaseDao;
import top.duyt.dto.Page;

public interface IRoleDao extends IbaseDao<Role> {

	/**
	 * 列表出所有的角色
	 * 
	 * @return
	 */
	public List<Role> listAllRoles();

	/**
	 * 获取用户组分页列表
	 * 
	 * @return
	 */
	public Page<Role> findRoles();

}
