package top.duyt.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IArticleDao;
import top.duyt.dto.Page;
import top.duyt.model.Article;

@Repository("articleDao")
public class ArticleDao extends BaseDao<Article> implements IArticleDao {

	private String getFindHql(){
		return "select new Article(a.id,a.title,a.author,a.cateName,a.isrecommend,a.status,a.creDate,a.publishDate,a.summary,a.isRepost) from Article a where 1=1";
	}
	
	@Override
	public Page<Article> find(Integer cid, String condition, Integer status) {
		return find(null, cid, condition, status,null,null);
	}
	
	@Override
	public Page<Article> find(Integer uid, Integer cid, String condition,
			Integer status,String beginTime,String endTime) {
		
		String hql = getFindHql();
		if(uid != null && uid != 0 ){
			hql = hql + " and a.user.id = " + uid; 
		}
		
		if(cid != null && cid != 0){
			hql = hql + " and a.category.id = " + cid; 
		}
		
		if(status != null){
			hql = hql + " and a.status = " + status; 
		}
		
		if (condition != null && !"".equals(condition)) {
			hql = hql + " and (a.keyword like '%" + condition + "%'" 
					+ " or a.title like '%" + condition + "%'"
			        + " or a.summary like '%" + condition + "%'"
			        + " or a.content like '%" + condition + "%')";
		}
		
		Map<String, Object> alias = new HashMap<String, Object>();
		if (beginTime != null && !"".equals(beginTime) && endTime != null && !"".equals(endTime)) {
			hql = hql + " and a.creDate between :beginTime and :endTime";
			alias.put("beginTime", beginTime);
			alias.put("endTime", endTime);
		}
		else{
			if(beginTime != null && !"".equals(beginTime)){
				hql = hql + " and a.creDate >= :beginTime";
				alias.put("beginTime", beginTime);
			}
			
			if(endTime != null && !"".equals(endTime)){
				hql = hql + " and a.creDate <= :endTime";
				alias.put("endTime", endTime);
			}
		}
		
		hql = hql + " order by a.creDate desc";
		return findByAlias(hql, alias);
	}

	@Override
	public Page<Article> findByCondition(String condition) {
		
		String hql = getFindHql();
		if (condition != null && !"".equals(condition)) {
			hql = hql + " and (a.title like '%" + condition 
			        + "%' or a.summary like '%" + condition 
			        + "%' or a.content like '%" + condition + "%') and a.status = 1";
		}
		
		return find(hql);
	}

	@Override
	public Page<Article> findrecommend(Integer cid) {
		
		String hql = getFindHql();
		if (cid != null && cid != 0) {
			hql = hql + " and a.category.id = " + cid;
		}
		
		hql = hql + " and a.isrecommend = 1 and a.status = 1";
		
		return find(hql);
	}

	@Override
	public Page<Article> findByKeyword(String keyword) {
		
		String hql = getFindHql();
		if (keyword != null && !"".equals(keyword)) {
			hql = hql + " and a.keyword like '%" + keyword + "%' ";
		}
		hql = hql + " and a.status = 1";
		
		return find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findLatest(Integer qty) {
		String hql = "from Article a order by a.publishDate desc";
		return getSession().createQuery(hql).setMaxResults(qty).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> findArtsCountsGroupByMonth() {
		String sql = "select date_format(t_article.cre_date,'%Y年%m月'),count(*) from  t_article group by date_format(t_article.cre_date,'%Y-%m')";
		return getSession().createSQLQuery(sql).list();
	}

}
