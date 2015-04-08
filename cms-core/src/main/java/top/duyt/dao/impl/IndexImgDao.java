package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IindexImgDao;
import top.duyt.model.IndexImg;

@Repository("indexImgDao")
public class IndexImgDao extends BaseDao<IndexImg> implements IindexImgDao {

	@Override
	public List<IndexImg> listAllIndexImgs() {
		String hql = "from IndexImg";
		return list(hql);
	}

	@Override
	public void resetOrders() {
		String hql = "update IndexImg ii set ii.sortNum = 0";
		updateByHql(hql);
	}

	@Override
	public List<IndexImg> listIndexImgsBySort() {
		String hql = "from IndexImg ii where ii.sortNum != 0 order by sortNum";
		return list(hql);
	}

	@Override
	public Integer findMaxSort() {
		String hql = "select max(ii.sortNum) from IndexImg ii";
		return (Integer) queryForObj(hql);
	}

}
