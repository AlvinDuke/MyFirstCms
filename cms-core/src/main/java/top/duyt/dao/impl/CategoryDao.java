package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.impl.BaseDao;
import top.duyt.dao.ICategoryDao;
import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;

@Repository("categoryDao")
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {

	@Override
	public List<Category> listCateByPcid(Integer pCid) {
		String hql = null;
		if (pCid == null || pCid == 0) {
			hql = "from Category c where c.category is null order by c.orders";
		}
		else{
			hql = "from Category c left join fetch c.category cp where cp.id = " + pCid +" order by c.orders";
		}
		return this.list(hql);
	}

	@Override
	public Integer findMaxOrdersUnderParent(Integer pCid) {
		
		String hql = null;
		if (pCid == null || pCid == 0) {
			hql = "select max(c.orders) from Category c where c.category is null";
		}
		else{
			hql = "select max(c.orders) from Category c where c.category.id = " + pCid;
		}
		
		return (Integer) this.queryForObj(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTreeDto> listAllCateTreeDto() {
		return this.listBySql("select c.id,c.name,c.orders,c.p_cid as pId "
				+ "from t_category c", CategoryTreeDto.class, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTreeDto> listCateTreeDtoByPid(Integer pid) {
		return this.listBySingleParamSql("select c.id,c.name,c.orders,c.p_cid as pId "
				+ "from t_category c where c.p_cid = ? order by c.orders", pid, CategoryTreeDto.class, false);
	}

	@Override
	public void updateCategorysOrders(Integer[] cids) {
		int newOrders = 1;
		String hql = "update Category c set c.orders = ? where c.id = ? ";
		for (Integer cid : cids) {
			this.updateByHql(hql, new Object[]{newOrders++,cid});
		}
	}

}
