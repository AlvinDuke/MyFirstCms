package top.duyt.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.util.file.File;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.stereotype.Service;

import top.duyt.dao.IArticleDao;
import top.duyt.dao.IAttachmentDao;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.Article;
import top.duyt.model.Attachment;
import top.duyt.service.IAttachmentService;

/**
 * 附件业务实现
 * 
 * @author Alvin Du
 * 
 */
@Service("attachmentService")
public class AttachmentService implements IAttachmentService {

	private IAttachmentDao attachmentDao;
	private IArticleDao articleDao;

	@Override
	public Attachment addAttachment(Attachment ach, String aid) {
		if(aid != null && !"".equals(aid)){
			Article a = articleDao.load(Integer.valueOf(aid));
			if (a != null) {
				ach.setArticle(a);
			}
		}
		return attachmentDao.add(ach);
	}
	
	@Override
	public void updateAttachmentToArticle(String aid, String tid) {
		Attachment dbAtt = attachmentDao.load(Integer.valueOf(tid));
		Article tempArt = new Article();
		tempArt.setId(Integer.valueOf(aid));
		dbAtt.setArticle(tempArt);
		attachmentDao.update(dbAtt);
	}

	@Override
	public void deleteAttachment(String aid) {
		//删除附件记录
		attachmentDao.delete(Integer.valueOf(aid));
		//删除对应的文件
		String rootPath = PageParamHolder.getRootPath();
		Attachment tempAtt = attachmentDao.load(Integer.valueOf(aid));
		String accessUrl = tempAtt.getAccessUrl().replace(PageParamHolder.getContextPath(), "");
		new File(rootPath + accessUrl).delete();
	}

	@Override
	public void deleteAttachmentsByAid(String aid) {
		attachmentDao.deleteAttachByAid(Integer.valueOf(aid));
	}
	
	@Override
	public List<Attachment> listAttachmentsByAid(String aid) {
		return attachmentDao.findAttachByAid(Integer.valueOf(aid));
	}
	
	@Override
	public List<String> listAttachmentIdsByAid(String aid) {
		return attachmentDao.findAttachIdsByAid(Integer.valueOf(aid));
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
