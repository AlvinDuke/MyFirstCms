/**
 * 
 */
package top.duyt.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import top.duyt.dao.IbaseDao;
import top.duyt.dto.Page;
import top.duyt.dto.PageParamHolder;

/**
 * @author Alvin Du
 *
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IbaseDao<T> {
	
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		//spring管理hibernate，使用getcurrentsession来获取
		//return sessionFactory.openSession();
		return sessionFactory.getCurrentSession();
	}
	
	private Class<T> clz;
	/**
	 * 获取泛型的类型
	 * @return
	 */
	private Class<T> getClz() {
		if (clz == null) {
			// 获取泛型的Class对象
			clz = ((Class<T>) (((ParameterizedType) (this.getClass()
					.getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	
	/**
	 * 设定hql的查询排序
	 * @param hql hql语句
	 * @return
	 */
	private String setOrder(String hql){
		//设置排序字段，正反序
		String sort = PageParamHolder.getSort();
		String order = PageParamHolder.getOrder();
		if (sort != null && !"".equals(sort)) {
			hql += " order by " + sort;
			if (order != null && !"".equals(order)) {
				hql += " " + order;
			}
			else{
				hql += " asc";  
			}
		}
		return hql;
	}
	
	/**
	 * 设定hibernate Query对象的别名参数
	 * @param q hibernate Query对象
	 * @param alias 别名参数
	 */
	@SuppressWarnings("rawtypes")
	private void setAliasParams(Query q, Map<String, Object> alias){
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					q.setParameterList(key, (Collection) val);
				}
				else{
					q.setParameter(key, val);
				}
			}
		}
	}
	
	/**
	 * 设定hibernate Query对象的数组参数
	 * @param q hibernate Query对象
	 * @param params 数组参数
	 */
	private void setArrayParams(Query q, Object[] params){
		if (params != null && params.length > 0) {
			int index = 0;
			for (Object param : params) {
				q.setParameter(index++, param);
			}
		}
	}
	
	/**
	 * 分页对象的参数设定，分别设定分页对象p和查询对象q的起始记录下标，分页大小
	 * @param q hibernate Query对象
	 * @param p Page分页对象
	 */
	private void setPageParams(Query q,Page<T> p){
		
		int pageSize = PageParamHolder.getPageSize();
		int offSet = PageParamHolder.getOffSet();
		
		if(pageSize == 0) pageSize = 10;
		if(offSet == 0) offSet = 0;
		p.setPageSize(pageSize);
		p.setOffSet(offSet);
		
		q.setFirstResult(offSet);
		q.setMaxResults(pageSize);
		
	}
	
	/**
	 * 构造传入hql的计数hql
	 * @param hql 原始hql
	 * @param isHql hql与否
	 * @return 已构造的计数hql
	 */
	private String getCountSql(String hql,boolean isHql){
		String oriSubstr = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*) " + oriSubstr;
		if (isHql) {
			countHql.replace("fetch", "");
		}
		return countHql;
	}
	
	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#add(java.lang.Object)
	 */
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		getSession().delete(this.load(id));
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#load(int)
	 */
	@Override
	public T load(int id) {
		return (T) getSession().load(getClz(), id);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#list(java.lang.String, java.lang.Object[])
	 */
	public List<T> listByArrayParams(String hql, Object[] params) {
		return this.listByMultiParams(hql, params, null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#list(java.lang.String, java.lang.Object)
	 */
	public List<T> listBySingleParam(String hql, Object param) {
		return this.listByMultiParams(hql, new Object[]{param},null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#list(java.lang.String)
	 */
	public List<T> list(String hql) {
		return this.listByMultiParams(hql, null, null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#list(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	public List<T> listByMultiParams(String hql, Object[] params, Map<String, Object> alias) {
		//设定查询排序
		hql = setOrder(hql);
		//创建query对象
		Query q = getSession().createQuery(hql);
		//设置别名参数
		setAliasParams(q,alias);
		//设置数组参数
		setArrayParams(q,params);
		return q.list();
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#list(java.lang.String, java.util.Map)
	 */
	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.listByMultiParams(hql, null, alias);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#find(java.lang.String, java.lang.Object[])
	 */
	public Page<T> findByArrayParams(String hql, Object[] params) {
		return this.findByMultiParams(hql, params, null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#find(java.lang.String, java.lang.Object)
	 */
	public Page<T> findBySingleParam(String hql, Object param) {
		return this.findByMultiParams(hql, new Object[]{param}, null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#find(java.lang.String)
	 */
	public Page<T> find(String hql) {
		return this.findByMultiParams(hql,null,null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#find(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	public Page<T> findByMultiParams(String hql, Object[] params, Map<String, Object> alias) {
		
		//分页对象
		Page<T> p = new Page<>();
		//设定查询排序
		hql = setOrder(hql);
		//创建query对象
		Query q = getSession().createQuery(hql);
		//设置别名参数
		setAliasParams(q,alias);
		//设置数组参数
		setArrayParams(q,params);
		//设定分页参数
		setPageParams(q,p);
		p.setDatas(q.list());
		
		//获取计数hql
		String countHql = getCountSql(hql,true);
		Query qCount = getSession().createQuery(countHql);
		//设置别名参数
		setAliasParams(qCount,alias);
		//设置数组参数
		setArrayParams(qCount,params);
		long totalRecords = (long) qCount.uniqueResult();
		//设定总记录数
		p.setTotalRecords(totalRecords);
		//设定总页数
		long tempRez = totalRecords/p.getPageSize();
		p.setTotalPages(tempRez>0?tempRez+1:tempRez);
		
		return p;
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#find(java.lang.String, java.util.Map)
	 */
	public Page<T> findByAlias(String hql, Map<String, Object> alias) {
		return this.findByMultiParams(hql, null, alias);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#queryForObj(java.lang.String, java.lang.Object[])
	 */
	public Object queryForObj(String hql, Object[] params) {
		Query q = getSession().createQuery(hql);
		setArrayParams(q, params);
		return q.uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#queryForObj(java.lang.String, java.lang.Object)
	 */
	public Object queryForObj(String hql, Object param) {
		return this.queryForObj(hql, new Object[]{param});
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#queryForObj(java.lang.String)
	 */
	public Object queryForObj(String hql) {
		return this.queryForObj(hql, null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#updateByHql(java.lang.String, java.lang.Object[])
	 */
	public void updateByHql(String hql, Object[] params) {
		Query q = getSession().createQuery(hql);
		setArrayParams(q, params);
		q.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#updateByHql(java.lang.String, java.lang.Object)
	 */
	public void updateByHql(String hql, Object param) {
		this.updateByHql(hql, new Object[]{param});
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#updateByHql(java.lang.String)
	 */
	public void updateByHql(String hql) {
		this.updateByHql(hql, null);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#listBySql(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
	 */
	@SuppressWarnings("rawtypes")
	public List listByArrayParamsSql(String sql, Object[] params, Class clz,
			boolean hasEntity) {
		return this.listByMultiParamsSql(sql, params, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#listBySql(java.lang.String, java.lang.Object, java.lang.Class, boolean)
	 */
	@SuppressWarnings("rawtypes")
	public List listBySingleParamSql(String sql, Object param, Class clz,
			boolean hasEntity) {
		return this.listByMultiParamsSql(sql, new Object[]{param}, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#listBySql(java.lang.String, java.lang.Class, boolean)
	 */
	@SuppressWarnings("rawtypes")
	public List listBySql(String sql, Class clz, boolean hasEntity) {
		return this.listByMultiParamsSql(sql, null, null, clz, hasEntity);
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#listBySql(java.lang.String, java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	@SuppressWarnings("rawtypes")
	public List listByMultiParamsSql(String sql, Object[] params,
			Map<String, Object> alias, Class clz, boolean hasEntity) {
		//设定查询排序
		sql = setOrder(sql);
		//创建query对象
		SQLQuery sq = getSession().createSQLQuery(sql);
		//设置别名参数
		setAliasParams(sq,alias);
		//设置数组参数
		setArrayParams(sq,params);
		//是否存在管理实体
		if (hasEntity) {
			sq.addEntity(clz);
		}
		else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		return sq.list();
	}

	/* (non-Javadoc)
	 * @see com.duyt.dao.IbaseDao#listBySql(java.lang.String, java.util.Map, java.lang.Class, boolean)
	 */
	@SuppressWarnings("rawtypes")
	public List listByAliasParamsSql(String sql, Map<String, Object> alias,
			Class clz, boolean hasEntity) {
		return this.listByMultiParamsSql(sql, null, alias, clz, hasEntity);
	}

	@SuppressWarnings("rawtypes")
	public Page findBySql(String sql, Class clz, boolean hasEntity) {
		return this.findByMultiParamsSql(sql, null, null, clz, hasEntity);
	}

	@SuppressWarnings("rawtypes")
	public Page findByAliasParamsSql(String sql, Map<String, Object> alias,
			Class clz, boolean hasEntity) {
		return this.findByMultiParamsSql(sql, null, alias, clz, hasEntity);
	}

	@SuppressWarnings("rawtypes")
	public Page findByArrayParamsSql(String sql, Object[] params,
			Class clz, boolean hasEntity) {
		return this.findByMultiParamsSql(sql, params, null, clz, hasEntity);
	}

	@SuppressWarnings("rawtypes")
	public Page findByMultiParamsSql(String sql, Object[] params,
			Map<String, Object> alias, Class clz, boolean hasEntity) {
		
		//分页对象
		Page<T> p = new Page<>();
		//设定查询排序
		sql = setOrder(sql);
		//创建query对象
		SQLQuery sq = getSession().createSQLQuery(sql);
		//设置别名参数
		setAliasParams(sq,alias);
		//设置数组参数
		setArrayParams(sq,params);
		//设定分页参数
		setPageParams(sq,p);
		//是否存在管理实体
		if (hasEntity) {
			sq.addEntity(clz);
		}else{
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		p.setDatas(sq.list());
		
		//获取计数Sql
		String countSql = getCountSql(sql,false);
		SQLQuery sqCount = getSession().createSQLQuery(countSql);
		//设置别名参数
		setAliasParams(sqCount,alias);
		//设置数组参数
		setArrayParams(sqCount,params);
		long totalRecords = ((BigInteger) sqCount.uniqueResult()).longValue();
		//设定总记录数
		p.setTotalRecords(totalRecords);
		//设定总页数
		long tempRez = totalRecords/p.getPageSize();
		p.setTotalPages(tempRez>0?tempRez+1:tempRez);
		
		return p;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
