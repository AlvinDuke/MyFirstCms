package top.duyt.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import top.duyt.Exception.CmsException;
import top.duyt.dao.IGroupDao;
import top.duyt.dao.IRoleDao;
import top.duyt.dao.IUserDao;
import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.service.IUserService;

import top.duyt.dto.Page;

/**
 * 用户数据实现
 * 
 * @author Alvin Du
 * 
 */
@Service("userService")
public class UserService implements IUserService {

	private IUserDao userDao;
	private IGroupDao groupDao;
	private IRoleDao roleDao;

	@Override
	public User addUser(User user, List<Integer> rids, List<Integer> gids) {
		User u = userDao.loadUserByUsername(user.getUsername());
		if (u != null)
			throw new CmsException("用户已存在，请重新添加");
		userDao.add(user);
		// 添加用户组信息
		for (Integer gid : gids) {
			Group g = groupDao.load(gid);
			if (g == null)
				throw new CmsException("选定的用户分组不存在");
			userDao.AddUserGroup(user, g);
		}
		// 添加用户角色信息
		for (Integer rid : rids) {
			Role r = roleDao.load(rid);
			if (r == null)
				throw new CmsException("选定的角色不存在");
			userDao.AddUserRole(user, r);
		}
		return user;
	}

	@Override
	public void deleteUser(int id) {
		// 删除用户前判断是否有文章，有文章只能停用 TODO
		userDao.delete(id);
		// 删除用户角色关系数据
		userDao.deleteUserRole(id);
		// 删除用户分组关系数据
		userDao.deleteUserGroup(id);
	}

	@Override
	public void updateUser(User user, List<Integer> rids, List<Integer> gids) {
		// 用户是否存在
		User u = userDao.loadUserByUsername(user.getUsername());
		if (u == null)
			throw new CmsException("要更新的用户不存在");
		// 更新用户角色关系数据
		userDao.deleteUserRole(u.getId());
		Role tempRole = null;
		for (Integer rid : rids) {
			tempRole = roleDao.load(rid);
			if (tempRole == null)
				throw new CmsException("要更新的角色信息不存在");
			userDao.AddUserRole(u, tempRole);
		}
		// 更新用户分组关系数据
		userDao.deleteUserGroup(u.getId());
		Group tempGroup = null;
		for (Integer gid : gids) {
			tempGroup = groupDao.load(gid);
			if (tempGroup == null)
				throw new CmsException("要更新的分组信息不存在");
			userDao.AddUserGroup(u, tempGroup);
		}
		// 更新用户信息
		userDao.update(user);
	}
	
	@Override
	public void updateSelf(User user) {
		userDao.update(user);
	}

	@Override
	public void updateUserStatus(int uid) {
		User u = userDao.load(uid);
		if (u == null)
			throw new CmsException("要更新状态的用户不存在");
		u.setStatus(u.getStatus().equals("0") ? "1" : "0");
		userDao.update(u);
	}

	public User loadUserByUid(int id) {
		return userDao.load(id);
	}

	@Override
	public User loadUserByUserName(String uName) {
		return userDao.loadUserByUsername(uName);
	}

	@Override
	public Page<User> findUsers() {
		return userDao.findUsers();
	}

	@Override
	public List<Integer> listUserRoleIds(int uid) {
		return this.userDao.listUserRoleIds(uid);
	}

	@Override
	public List<Integer> listUserGroupIds(int uid) {
		return userDao.listUserGroupIds(uid);
	}

	@Override
	public List<Role> ListRolesByUid(int uid) {
		return userDao.listUserRoles(uid);
	}

	@Override
	public List<Group> ListGroupByUid(int uid) {
		return userDao.listUserGroups(uid);
	}

	@Override
	public List<User> ListUserByRid(int rid) {
		return userDao.listUserByRoleId(rid);
	}

	@Override
	public List<User> ListGroupByGid(int gid) {
		return userDao.listUserByGroupId(gid);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	@Inject
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
