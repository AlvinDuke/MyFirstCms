package top.duyt.dao;

import java.util.List;

import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.model.UserGroups;
import top.duyt.model.UserRoles;
import top.duyt.model.Emur.RoleType;

import top.duyt.dao.IbaseDao;
import top.duyt.dto.Page;

public interface IUserDao extends IbaseDao<User> {

	/**
	 * 查询当前用户包含的所有角色
	 * 
	 * @param uid
	 *            用户id
	 * @return
	 */
	public List<Role> listUserRoles(int uid);

	/**
	 * 查询当前用户包含的所有角色的id
	 * 
	 * @param uid
	 *            用户id
	 * @return
	 */
	public List<Integer> listUserRoleIds(int uid);

	/**
	 * 查询当前用户包含的所有组
	 * 
	 * @param uid
	 *            用户id
	 * @return
	 */
	public List<Group> listUserGroups(int uid);

	/**
	 * 查询当前用户包含的所有组
	 * 
	 * @param uid
	 *            用户id
	 * @return
	 */
	public List<Integer> listUserGroupIds(int uid);

	/**
	 * 查询某个用户角色的详细信息
	 * 
	 * @param uid
	 *            用户id
	 * @param rid
	 *            角色id
	 * @return
	 */
	public UserRoles loadUserRoles(int uid, int rid);

	/**
	 * 查询某个用户组的详细信息
	 * 
	 * @param uid
	 * @param gid
	 * @return
	 */
	public UserGroups loadUserGroups(int uid, int gid);

	/**
	 * 根据用户名加载用户
	 * 
	 * @param username
	 * @return
	 */
	public User loadUserByUsername(String username);

	/**
	 * 根据角色id查询用户
	 * 
	 * @param rid
	 *            角色id
	 * @return
	 */
	public List<User> listUserByRoleId(int rid);

	/**
	 * 根据角色枚举查询用户
	 * 
	 * @param rid
	 *            角色枚举
	 * @return
	 */
	public List<User> listUserByRoleType(RoleType rt);

	/**
	 * 查询分组内的用户
	 * 
	 * @param gid
	 *            组id
	 * @return
	 */
	public List<User> listUserByGroupId(int gid);

	/**
	 * 获取用户分页列表
	 * 
	 * @return
	 */
	public Page<User> findUsers();

	/**
	 * 添加用户角色关系信息
	 * 
	 * @param u
	 * @param r
	 */
	public UserRoles AddUserRole(User u, Role r);

	/**
	 * 添加用户分组关系信息
	 * 
	 * @param u
	 * @param g
	 */
	public UserGroups AddUserGroup(User u, Group g);

	/**
	 * 删除某个用户所对应的所有用户角色关系数据
	 * 
	 * @param uid
	 *            用户id
	 */
	public void deleteUserRole(int uid);

	/**
	 * 删除某个用户所对应的所有用户组关系数据
	 * 
	 * @param uid
	 *            用户id
	 */
	public void deleteUserGroup(int uid);
}
