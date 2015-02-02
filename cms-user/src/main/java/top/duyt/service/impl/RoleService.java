package top.duyt.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import top.duyt.Exception.CmsException;
import top.duyt.dao.IRoleDao;
import top.duyt.dao.IUserDao;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.service.IRoleService;

import top.duyt.dto.Page;

/**
 * 角色数据服务实现
 * 
 * @author Alvin Du
 * 
 */
@Service("roleService")
public class RoleService implements IRoleService {

	private IRoleDao roleDao;
	private IUserDao userDao;

	@Override
	public void addRole(Role role) {
		roleDao.add(role);
	}

	@Override
	public Role loadRole(int rid) {
		return roleDao.load(rid);
	}

	@Override
	public void updateRole(Role r) {
		roleDao.update(r);
	}

	@Override
	public void deleteRole(int rid) {
		List<User> users = userDao.listUserByRoleId(rid);
		if (users != null && users.size() > 0) {
			throw new CmsException("角色下含有至少一个用户，不可以删除");
		} else {
			roleDao.delete(rid);
		}
	}

	@Override
	public List<Role> listAllRoles() {
		return roleDao.listAllRoles();
	}

	@Override
	public Page<Role> findRoles() {
		return roleDao.findRoles();
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
