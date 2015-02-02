package top.duyt.dao;

/**
 * 公共Dao接口
 * @author Alvin Du
 *
 * @param <T>
 */
public interface IbaseDao<T> {
	
	/**
	 * 公共Dao接口方法：新增一个对象
	 * @param t 待新增对象
	 * @return
	 */
	public T add(T t);
	
	/**
	 * 公共Dao接口方法：根据id删除一个对象
	 * @param id 待删除对象id
	 */
	public void delete(int id);
	
	/**
	 * 公共Dao接口方法：修改一个对象
	 * @param t 待修改对象
	 */
	public void update(T t);
	
	/**
	 * 公共Dao接口方法：加载一个对象
	 * @param id 待加载对象的id
	 * @return
	 */
	public T load(int id);
	
	//公共Dao暴露过多接口，特定模块中无需暴露冗余的接口，注释
	
	/**
	 * 根据多个参数，不分页查找一组对象
	 * @param hql 查询hql
	 * @param params 查询参数
	 * @return
	 *//*
	public List<T> listByArrayParams(String hql,Object[] params);
	
	*//**
	 * 根据一个参数，不分页查找一组对象
	 * @param hql 查询hql
	 * @param param 查询参数
	 * @return
	 *//*
	public List<T> listBySingleParam(String hql,Object param);
	
	*//**
	 * 不分页直接查找一组对象
	 * @param hql 查询hql
	 * @return
	 *//*
	public List<T> list(String hql);
	
	*//**
	 * 根据数组或别名参数查找一组对象
	 * @param hql 查询hql
	 * @param params 数据参数
	 * @param alias 别名参数
	 * @return
	 *//*
	public List<T> listByMultiParams(String hql,Object[] params,Map<String, Object> alias);
	
	*//**
	 * 根据别名参数查找一组对象
	 * @param hql 查询hql
	 * @param alias 别名参数
	 * @return
	 *//*
	public List<T> listByAlias(String hql,Map<String, Object> alias);

	
	*//**
	 * 根据多个参数，分页查找一组对象
	 * @param hql 查询hql
	 * @param params 查询参数
	 * @return
	 *//*
	public Page<T> findByArrayParams(String hql,Object[] params);
	
	*//**
	 * 根据一个参数，分页查找一组对象
	 * @param hql 查询hql
	 * @param param 查询参数
	 * @return
	 *//*
	public Page<T> findBySingleParam(String hql,Object param);
	
	*//**
	 * 直接分页查找一组对象
	 * @param hql 查询hql
	 * @return
	 *//*
	public Page<T> find(String hql);
	
	*//**
	 * 根据数组或别名参数查找分页一组对象
	 * @param hql 查询hql
	 * @param params 数组参数
	 * @param alias 别名参数
	 * @return
	 *//*
	public Page<T> findByMultiParams(String hql,Object[] params,Map<String, Object> alias);
	
	*//**
	 * 根据别名参数查找分页一组对象
	 * @param hql 查询hql
	 * @param alias 别名参数
	 * @return
	 *//*
	public Page<T> findByAlias(String hql,Map<String, Object> alias);
	
	*//**
	 * 根据多个参数查找一个对象
	 * @param hql 查询hql
	 * @param params 数组参数
	 * @return
	 *//*
	public Object queryForObj(String hql,Object[] params);
	
	*//**
	 * 根据一个参数查找一个对象
	 * @param hql 查询hql
	 * @param param 参数
	 * @return
	 *//*
	public Object queryForObj(String hql,Object param);
	
	*//**
	 * 直接查找一个对象
	 * @param hql 查询hql
	 * @return
	 *//*
	public Object queryForObj(String hql);
	
	*//**
	 * 通过hql更新一组对象
	 * @param hql 更新hql
	 * @param params 数组参数
	 *//*
	public void updateByHql(String hql,Object[] params);
	
	*//**
	 * 通过hql更新一个对象
	 * @param hql 更新hql
	 * @param param 参数
	 *//*
	public void updateByHql(String hql,Object param);
	
	*//**
	 * 通过hql直接更新一组对象
	 * @param hql 更新hql
	 *//*
	public void updateByHql(String hql);
	
	*//**
	 * 通过原生sql，一组参数，查询一组对象
	 * @param sql 查询sql
	 * @param params 数组参数
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public List<T> listByArrayParamsSql(String sql,Object[] params,Class<T> clz,boolean hasEntity);
	

	*//**
	 * 通过原生sql，一个参数，查询一组对象
	 * @param sql 查询sql
	 * @param param 参数
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public List<T> listBySingleParamSql(String sql,Object param,Class<T> clz,boolean hasEntity);

	*//**
	 * 直接通过原生sql查询一组对象
	 * @param sql 查询sql
	 * @param params 数组参数
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public List<T> listBySql(String sql,Class<T> clz,boolean hasEntity);
	
	*//**
	 * 通过原生sql，数组或者别名参数，查询一组对象
	 * @param sql 查询sql
	 * @param params 数组参数
	 * @param alias 别名参数
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public List<T> listByMultiParamsSql(String sql,Object[] params,Map<String, Object> alias, Class<T> clz,boolean hasEntity);
	
	*//**
	 * 通过原生sql，别名参数，查询一组对象
	 * @param sql 查询sql
	 * @param alias 别名参数
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public List<T> listByAliasParamsSql(String sql,Map<String, Object> alias, Class<T> clz,boolean hasEntity);
	
	*//**
	 * 通过原生sql获取分页对象
	 * @param sql 查询sql
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public Page<T> findBySql(String sql,Class<T> clz,boolean hasEntity);
	
	*//**
	 * 通过原生sql，别名参数获取分页对象
	 * @param sql 查询sql
	 * @param alias 别名参数列表
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public Page<T> findByAliasParamsSql(String sql,Map<String, Object> alias, Class<T> clz,boolean hasEntity);
	
	*//**
	 * 通过原生sql，数组参数获取分页对象
	 * @param sql 查询sql
	 * @param params 数组参数列表
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public Page<T> findByArrayParamsSql(String sql,Object[] params, Class<T> clz,boolean hasEntity);
	
	*//**
	 * 通过原生sql，数组参数获取分页对象
	 * @param sql 查询sql
	 * @param params 数组参数列表
	 * @param alias 别名参数列表
	 * @param clz 查询的实体对象
	 * @param hasEntity 是否存在hibernate管理的实体
	 * @return
	 *//*
	public Page<T> findByMultiParamsSql(String sql,Object[] params,Map<String, Object> alias, Class<T> clz,boolean hasEntity);
	*/
}
