package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IGroupDao;
import top.duyt.model.Group;

import top.duyt.dao.impl.BaseDao;
import top.duyt.dto.Page;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> listAllGroups() {
		return (List<Group>) this.getSession().createQuery("from Group").list();
	}

	@Override
	public Page<Group> findGroups() {
		return this.find("from Group");
	}

}
