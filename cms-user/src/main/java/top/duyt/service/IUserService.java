package top.duyt.service;

import java.util.List;

import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;

import top.duyt.dto.Page;

/**
 * 用户数据相关服务
 * @author Alvin Du
 *
 */
public interface IUserService {
	
	/**
	 * 新增一个用户对象
	 * @param user 用户对象
	 * @param rids 角色id
	 * @param gids 组id
	 * @return
	 */
	public User addUser(User user,List<Integer> rids,List<Integer> gids);
	
	/**
	 * 删除一个用户
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * 更新一个用户
	 * @param user 待更新用户
	 * @param rids 更新后的用户角色
	 * @param gids 更新后的用户组
	 */
	public void updateUser(User user,List<Integer> rids,List<Integer> gids);
	
	/**
	 * 更新个人信息
	 * @param user
	 */
	public void updateSelf(User user);
	
	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	public User loadUserByUid(int id);
	
	/**
	 * 根据用户名称获取用户
	 * @param uName
	 * @return
	 */
	public User loadUserByUserName(String uName);
	
	/**
	 * 获取用户分页列表
	 * @return
	 */
	public Page<User> findUsers();
	
	/**
	 * 更新用户的状态
	 * @param uid
	 */
	public void updateUserStatus(int uid);
	
	/**
	 * 查询当前用户包含的所有角色的id
	 * @param uid 用户id
	 * @return
	 */
	public List<Integer> listUserRoleIds(int uid);
	
	/**
	 * 查询当前用户包含的所有组
	 * @param uid 用户id
	 * @return
	 */
	public List<Integer> listUserGroupIds(int uid);
	
	/**
	 * 获取用户包含的角色信息
	 * @param uid
	 * @return
	 */
	public List<Role> ListRolesByUid(int uid);
	
	/**
	 * 获取用户包含的分组信息
	 * @param uid
	 * @return
	 */
	public List<Group> ListGroupByUid(int uid);
	
	/**
	 * 获取一个角色下包含的所有用户
	 * @param rid
	 * @return
	 */
	public List<User> ListUserByRid(int rid);
	
	/**
	 * 获取一个组内的所有用户
	 * @param gid
	 * @return
	 */
	public List<User> ListGroupByGid(int gid);
	

}
