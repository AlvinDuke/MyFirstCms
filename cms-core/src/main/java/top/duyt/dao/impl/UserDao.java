package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IUserDao;
import top.duyt.model.Group;
import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.model.UserGroups;
import top.duyt.model.UserRoles;
import top.duyt.model.Emur.RoleType;

import top.duyt.dao.impl.BaseDao;
import top.duyt.dto.Page;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public List<Role> listUserRoles(int uid) {
		return this
				.getSession()
				.createQuery(
						"select ur.role from UserRoles ur where ur.user.id = ?")
				.setParameter(0, uid).list();
	}

	@Override
	public List<Integer> listUserRoleIds(int uid) {
		return this
				.getSession()
				.createQuery(
						"select ur.role.id from UserRoles ur where ur.user.id = ?")
				.setParameter(0, uid).list();
	}

	@Override
	public List<Group> listUserGroups(int uid) {
		return this
				.getSession()
				.createQuery(
						"select ug.group from UserGroups ug where ug.user.id = ?")
				.setParameter(0, uid).list();
	}

	@Override
	public List<Integer> listUserGroupIds(int uid) {
		return this
				.getSession()
				.createQuery(
						"select ug.group.id from UserGroups ug where ug.user.id = ?")
				.setParameter(0, uid).list();
	}

	@Override
	public UserRoles loadUserRoles(int uid, int rid) {
		return (UserRoles) this
				.getSession()
				.createQuery(
						"from UserRoles ur left join fetch ur.user u left join fetch ur.role r "
								+ "where u.id = ? and r.id = ?")
				.setParameter(0, uid).setParameter(1, rid).uniqueResult();
	}

	@Override
	public UserGroups loadUserGroups(int uid, int gid) {
		return (UserGroups) this
				.getSession()
				.createQuery(
						"from UserGroups ug left join fetch ug.user u left join fetch ug.group g "
								+ "where u.id = ? and g.id = ?")
				.setParameter(0, uid).setParameter(1, gid).uniqueResult();
	}

	@Override
	public User loadUserByUsername(String username) {
		return (User) this.getSession()
				.createQuery("from User u where u.username = ?")
				.setParameter(0, username).uniqueResult();
	}

	@Override
	public List<User> listUserByRoleId(int rid) {
		return this.listBySingleParam(
				"select ur.user from UserRoles ur where ur.role.id = ?", rid);
	}

	@Override
	public List<User> listUserByRoleType(RoleType rt) {
		return this.listBySingleParam(
				"select ur.user from UserRoles ur where ur.role.roleType = ?",
				rt);
	}

	@Override
	public List<User> listUserByGroupId(int gid) {
		return this.listBySingleParam(
				"select ug.user from UserGroups ug where ug.group.id = ?", gid);
	}

	@Override
	public UserRoles AddUserRole(User u, Role r) {
		UserRoles ur = new UserRoles();
		ur.setUser(u);
		ur.setRole(r);
		this.getSession().save(ur);
		return ur;
	}

	@Override
	public UserGroups AddUserGroup(User u, Group g) {
		UserGroups ug = new UserGroups();
		ug.setGroup(g);
		ug.setUser(u);
		this.getSession().save(ug);
		return ug;
	}

	@Override
	public Page<User> findUsers() {
		return this.find("from User");
	}

	@Override
	public void deleteUserRole(int uid) {
		this.updateByHql("delete UserRoles ur where ur.user.id = ? ", uid);
	}

	@Override
	public void deleteUserGroup(int uid) {
		this.updateByHql("delete UserGroups ug where ug.user.id = ? ", uid);
	}

}
