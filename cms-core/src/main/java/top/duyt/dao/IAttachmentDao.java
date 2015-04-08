package top.duyt.dao;

import java.util.List;

import top.duyt.model.Attachment;

public interface IAttachmentDao extends IbaseDao<Attachment>{
	
	/**
	 * 根据文章id获取文章的附件信息
	 * @param aid
	 * @return
	 */
	public List<Attachment> findAttachByAid(Integer aid);
	
	/**
	 * 根据文章id获取文章的附件id
	 * @param aid
	 * @return
	 */
	public List<String> findAttachIdsByAid(Integer aid);
	
	/**
	 * 根据文章的id删除文章包含的附件信息
	 * @param aid
	 */
	public void deleteAttachByAid(Integer aid);

}
