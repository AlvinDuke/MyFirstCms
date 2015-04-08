package top.duyt.dao;

import java.util.Date;
import java.util.List;

import top.duyt.dto.Page;
import top.duyt.model.Article;

public interface IArticleDao extends IbaseDao<Article> {

	/**
	 * 根据文章所属的栏目，关键字和状态查找相应的文章
	 * 
	 * @param cid
	 * @param keyword
	 * @param status
	 * @return
	 */
	public Page<Article> find(Integer cid, String condition, Integer status);

	/**
	 * 根据文章所属的栏目，文章所属的用户，关键字和状态查找相应的文章
	 * 
	 * @param uid
	 * @param cid
	 * @param keyword
	 * @param status
	 * @return
	 */
	public Page<Article> find(Integer uid, Integer cid, String condition,
			Integer status,String beginTime,String endTime);

	/**
	 * 根据关键字查找，查找范围是文章内容，标题，和摘要
	 * 
	 * @param keyword
	 * @return
	 */
	public Page<Article> findByCondition(String condition);
	
	/**
	 * 根据文章关键字查找
	 * @param keyword
	 * @return
	 */
	public Page<Article> findByKeyword(String keyword);

	/**
	 * 获取某个栏目下的推荐文章，没有设定栏目则获取全部的推荐文章
	 * 
	 * @param cid
	 * @return
	 */
	public Page<Article> findrecommend(Integer cid);
	
	/**
	 * 获取指定个数的最新文章
	 * @param qty
	 * @return
	 */
	public List<Article> findLatest(Integer qty);
	
	/**
	 * 获取每个月文章的数量，并按月份分组
	 * @return List<String[]> 每个元素：月份/月份包含的文章数量
	 */
	public List<String[]> findArtsCountsGroupByMonth();

}
