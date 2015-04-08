package top.duyt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import top.duyt.dao.IAttachmentDao;
import top.duyt.model.Attachment;

@Repository("attachmentDao")
public class AttachmentDao extends BaseDao<Attachment> implements IAttachmentDao{

	@Override
	public List<Attachment> findAttachByAid(Integer aid) {
		String hql = "from Attachment a where a.article.id = ?";
		return listBySingleParam(hql, aid);
	}

	@Override
	public void deleteAttachByAid(Integer aid) {
		String hql = "delete Attachment a where a.article.id = ?";
		this.getSession().createQuery(hql).setInteger(0, aid).executeUpdate();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> findAttachIdsByAid(Integer aid) {
		String hql = "select a.id from Attachment a where a.article.id = ?";
		return getSession().createQuery(hql).setInteger(0, aid).list();
	}

}
