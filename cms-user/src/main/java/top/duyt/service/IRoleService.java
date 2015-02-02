package top.duyt.service;

import java.util.List;

import top.duyt.model.Role;

import top.duyt.dto.Page;

/**
 * 角色数据相关服务
 * @author Alvin Du
 *
 */
public interface IRoleService {
	
	/**
	 * 新增一个角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 删除一个角色
	 * @param rid
	 */
	public void deleteRole(int rid);
	
	/**
	 * 更新一个角色
	 * @param r
	 */
	public void updateRole(Role r); 
	
	/**
	 * 获取一个角色信息
	 * @param rid
	 */
	public Role loadRole(int rid);
	
	/**
	 * 列表所有角色
	 * @return
	 */
	public List<Role> listAllRoles();
	
	/**
	 * 获取用户角色分页列表
	 * @return
	 */
	public Page<Role> findRoles();

}
