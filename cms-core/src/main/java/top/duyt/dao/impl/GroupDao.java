package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IGroupDao;
import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Group;
import top.duyt.model.GroupCategory;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupCategory> listGroupCategoriesByGid(Integer gid) {
		String hql = "from GroupCategory gc where gc.group.id = ?";
		return this.getSession().createQuery(hql).setParameter(0, gid).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTreeDto> listCateTreeByGid(Integer gid) {
		String sql = "select c.id as id,c.name as name,c.orders as orders,c.p_cid as pId"
				+ " from t_group_category gc left join t_category c on c.id = gc.c_id where gc.g_id = ?";
		return this.listBySingleParamSql(sql, gid, CategoryTreeDto.class, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTreeDto> listCateTreeByUid(Integer uid) {
		String sql = "select c.id as id,c.name as name,c.orders as orders,c.p_cid as pId "
				+ "from t_user_groups ug left join t_group g on ug.g_id = g.id left join t_group_category gc"
				+ " on g.id = gc.g_id left join t_category c on gc.c_id = c.id where ug.u_id = ?";
		return this.listBySingleParamSql(sql, uid, CategoryTreeDto.class, false);
	}

	@Override
	public GroupCategory addGroupCategory(Integer gid,Integer cid) {
		GroupCategory gc = new GroupCategory();
		Group g = load(gid);
		Category c = (Category) this.getSession().createQuery("from Category c where c.id = " + cid).uniqueResult();
		gc.setGroup(g);
		gc.setCategory(c);
		this.getSession().save(gc);
		return gc;
	}

	@Override
	public void deleteGroupCategory(Integer gid,Integer cid) {
		this.updateByHql("delete GroupCategory gc where gc.group.id = ? "
				+ "and gc.category.id = ?", new Object[]{gid,cid});
	}

	@Override
	public GroupCategory loadGroupCategory(Integer gid, Integer cid) {
		return (GroupCategory) this.getSession().
									createQuery("from GroupCategory gc "
											+ "where gc.group.id = ? "
											+ "and gc.category.id = ?")
											.setParameter(0, gid)
											.setParameter(1, cid).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTreeDto> generateCateTreeByGid(Integer gid) {
		
		String sql = "select c.id as id,c.name as name,c.orders as orders,c.p_cid as pId"
				+ " from t_group_category gc left join t_category c on c.id = gc.c_id where gc.g_id = ?"
				+ " union all"
				+ " select distinct cp.id as id,cp.name as name,cp.orders as orders,cp.p_cid as pId"
				+ " from t_group_category gc left join t_category c on c.id = gc.c_id "
				+ " left join t_category cp on c.p_cid = cp.id where gc.g_id = ? and cp.id is not null";
		return this.listByArrayParamsSql(sql, new Object[]{gid,gid}, CategoryTreeDto.class, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTreeDto> generateCateTreeByUid(Integer uid) {
		String sql = "select c.id as id,c.name as name,c.orders as orders,c.p_cid as pId "
				+ "from t_user_groups ug left join t_group g on ug.g_id = g.id left join t_group_category gc"
				+ " on g.id = gc.g_id left join t_category c on gc.c_id = c.id where ug.u_id = ?"
			 	+ " union all"
			 	+ " select distinct cp.id as id,cp.name as name,cp.orders as orders,cp.p_cid as pId "
				+ "	from t_user_groups ug left join t_group g on ug.g_id = g.id left join t_group_category gc"
				+ "	on g.id = gc.g_id left join t_category c on gc.c_id = c.id left join t_category cp on"
				+ "	cp.id = c.p_cid where ug.u_id = ?";
		return this.listByArrayParamsSql(sql, new Object[]{uid,uid}, CategoryTreeDto.class, false);
	}


}
