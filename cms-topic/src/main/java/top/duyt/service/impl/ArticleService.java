package top.duyt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.util.file.File;
import org.springframework.stereotype.Service;

import top.duyt.Exception.CmsException;
import top.duyt.dao.IArticleDao;
import top.duyt.dao.IAttachmentDao;
import top.duyt.dao.ICategoryDao;
import top.duyt.dao.IUserDao;
import top.duyt.dto.Page;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.Article;
import top.duyt.model.Attachment;
import top.duyt.model.Category;
import top.duyt.model.User;
import top.duyt.service.IArticleService;

/**
 * 文章的业务实现
 * 
 * @author Alvin Du
 * 
 */
@Service("articleService")
public class ArticleService implements IArticleService {

	private ICategoryDao categoryDao;
	private IUserDao userDao;
	private IAttachmentDao attachmentDao;
	private IArticleDao articleDao;

	/**
	 * 添加文章的附件信息
	 * 
	 * @param attachId
	 * @param art
	 */
	private void addArticleAtts(String[] attachId, Article art) {
		// 添加文章的附件信息
		if (attachId != null && !"".equals(attachId)) {
			Attachment att = null;
			for (String aid : attachId) {
				if(aid != null && !"".equals(aid)){
					att = attachmentDao.load(Integer.valueOf(aid));
					att.setArticle(art);
				}
			}
		}
	}

	@Override
	public Article addArticle(Article art, Integer cid, Integer uid,
			String[] attachId) {

		Category c = categoryDao.load(cid);
		User u = userDao.load(uid);
		if (c == null || u == null)
			throw new CmsException("添加文章的所属栏目或者所属用户不存在");
		// 设置对象的栏目名和用户名
		art.setAuthor(u.getNickname());
		art.setCateName(c.getName());
		art.setCategory(c);
		art.setUser(u);
		// 添加文章的附件
		addArticleAtts(attachId, art);
		return articleDao.add(art);
	}

	@Override
	public Article addArticle(Article art, Integer cid, Integer uid) {
		return this.addArticle(art, cid, uid, null);
	}

	@Override
	public void deleteArticle(Integer aid) {
		// 删除文章
		articleDao.delete(aid);
		// 删除文章包含附件的所有记录
		attachmentDao.deleteAttachByAid(aid);
		// 获取文章包含的所有附件,逐个删除磁盘上的文件
		List<Attachment> as = attachmentDao.findAttachByAid(aid);
		for (Attachment att : as) {
			String rootPath = PageParamHolder.getRootPath()
					+ att.getAccessUrl().replace(PageParamHolder.getContextPath(),"");
			new File(rootPath).delete();
		}
	}

	@Override
	public void updateArticle(Article art, Integer cid, String[] attachId) {
		Category c = categoryDao.load(cid);
		art.setCategory(c);
		art.setCateName(c.getName());
		articleDao.update(art);
		// 添加文章的附件
		addArticleAtts(attachId, art);
	}

	@Override
	public void updateArticle(Article art, Integer cid) {
		updateArticle(art, cid, null);
	}
	
	@Override
	public void updateArticle(Article art) {
		articleDao.update(art);
	}

	@Override
	public Article loadArticle(int id) {
		return articleDao.load(id);
	}

	@Override
	public Page<Article> find(Integer cid, String keyword, Integer status) {
		return articleDao.find(cid, keyword, status);
	}
	
	@Override
	public Page<Article> find(Integer cid, String keyword, Integer status,
			String dateCondition) {
		
		//全部文章
		if(cid == 9999){
			cid = 0;
		}
		
		//是否根据日期查询
		String beginTime = null;
		String endTime = null;
		if(dateCondition!=null&&!"".equals(dateCondition)){
			
			SimpleDateFormat sdfShort = new SimpleDateFormat("yyyy年MM月");
			SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				
				Date tempD = sdfShort.parse(dateCondition);
				Calendar c = Calendar.getInstance();
				c.setTime(tempD);
				//起始时间
				beginTime = sdfFull.format(tempD);
				
				//结束时间
				c.add(Calendar.MONTH, 1);
				endTime = sdfFull.format(c.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return articleDao.find(null, cid, keyword, status, beginTime, endTime);
	}

	@Override
	public Page<Article> find(Integer uid, Integer cid, String keyword,
			Integer status) {
		return articleDao.find(uid, cid, keyword, status,null,null);
	}

	@Override
	public Page<Article> findByCondition(String condition) {
		return articleDao.findByCondition(condition);
	}

	@Override
	public Page<Article> findByKeyword(String keyword) {
		return articleDao.findByKeyword(keyword);
	}

	@Override
	public Page<Article> findrecommend(Integer cid) {
		return articleDao.findrecommend(cid);
	}

	@Override
	public List<Article> findLatest(Integer qty) {
		return articleDao.findLatest(qty);
	}
	
	@Override
	public List<String[]> findArtsCountsGroupByMonth() {
		return articleDao.findArtsCountsGroupByMonth();
	}
	
	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Inject
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IAttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	@Inject
	public void setAttachmentDao(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public IArticleDao getArticleDao() {
		return articleDao;
	}

	@Inject
	public void setArticleDao(IArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
