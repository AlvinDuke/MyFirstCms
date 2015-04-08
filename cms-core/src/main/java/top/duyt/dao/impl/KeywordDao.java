package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IKeywordDao;
import top.duyt.model.Keyword;

@Repository("keywordDao")
public class KeywordDao extends BaseDao<Keyword> implements IKeywordDao {

	@Override
	public List<Keyword> findKeywordByCondition(String condition) {
		String hql = "from Keyword k where k.keyword like '%" + condition
				+ "%'";
		return list(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findKeywords(String condition) {
		String hql = "select distinct k.keyword from Keyword k where k.keyword like '%"
				+ condition + "%' or k.kwShortPy like '%" + condition
				+ "%' or k.kwFullPy like '%" + condition + "%'";
		return this.getSession().createQuery(hql).list();
	}

}
