package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IRoleDao;
import top.duyt.model.Role;

import top.duyt.dao.impl.BaseDao;
import top.duyt.dto.Page;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> listAllRoles() {
		return (List<Role>) this.getSession().createQuery("from Role").list();
	}

	@Override
	public Page<Role> findRoles() {
		return this.find("from Role");
	}

}
