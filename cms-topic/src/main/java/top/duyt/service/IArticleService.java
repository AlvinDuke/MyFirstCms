package top.duyt.service;

import java.util.Date;
import java.util.List;

import top.duyt.dto.Page;
import top.duyt.model.Article;

public interface IArticleService{

	/**
	 * 添加一篇文章，带附件信息
	 * @param art 文章
	 * @param cid 所属栏目
	 * @param uid 所属用户
	 * @param attachId 附件信息
	 * @return
	 */
	public Article addArticle(Article art,Integer cid,Integer uid,String[] attachId);
	
	/**
	 * 添加一篇文章，不带附件信息
	 * @param art 文章
	 * @param cid 所属栏目
	 * @param uid 所属用户
	 * @return
	 */
	public Article addArticle(Article art,Integer cid,Integer uid);
	
	/**
	 * 删除文章
	 * @param aid
	 */
	public void deleteArticle(Integer aid);
	
	/**
	 * 更新文章（含修改附件）
	 * @param art 文章
	 * @param cid 栏目id
	 * @param attachId 附件id
	 */
	public void updateArticle(Article art,Integer cid,String[] attachId);
	
	/**
	 * 更新文章（不含修改附件）
	 * @param art
	 * @param cid
	 */
	public void updateArticle(Article art,Integer cid);
	
	/**
	 * 更新文章
	 * @param art
	 */
	public void updateArticle(Article art);
	
	/**
	 * 获取一个文章内容
	 * @param id
	 * @return
	 */
	public Article loadArticle(int id);
	
	/**
	 * 根据文章所属的栏目，关键字和状态查找相应的文章
	 * 
	 * @param cid
	 * @param keyword
	 * @param status
	 * @return
	 */
	public Page<Article> find(Integer cid, String keyword, Integer status);

	/**
	 * 根据文章所属的栏目，关键字，状态和日期（查找日期往后一个月之内的）查找相应的文章
	 * 
	 * @param cid
	 * @param keyword
	 * @param status
	 * @param dateCondition
	 * @return
	 */
	public Page<Article> find(Integer cid, String keyword,Integer status,String dateCondition);
	
	/**
	 * 根据文章所属的栏目，文章所属的用户，关键字和状态查找相应的文章
	 * 
	 * @param uid
	 * @param cid
	 * @param keyword
	 * @param status
	 * @return
	 */
	public Page<Article> find(Integer uid, Integer cid, String keyword,
			Integer status);

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
