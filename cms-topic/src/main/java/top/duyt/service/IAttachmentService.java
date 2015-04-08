package top.duyt.service;

import java.util.List;

import top.duyt.model.Attachment;

public interface IAttachmentService {
	
	/**
	 * 为文章添加附件
	 * @param ach
	 * @param aid
	 * @return
	 */
	public Attachment addAttachment(Attachment ach,String aid);
	
	/**
	 * 将指定附件绑定到指定文章
	 * @param aid
	 * @param tid
	 */
	public void updateAttachmentToArticle(String aid,String tid);
	
	/**
	 * 查询所有属于指定文章的附件
	 * @param aid
	 * @return
	 */
	public List<Attachment> listAttachmentsByAid(String aid);
	
	/**
	 * 查询所有属于指定文章的附件id
	 * @param aid
	 * @return
	 */
	public List<String> listAttachmentIdsByAid(String aid);

	/**
	 * 删除一个指定的附件
	 * @param aid 附件id
	 */
	public void deleteAttachment(String aid);
	
	/**
	 * 删除一个文章的所有附件
	 * @param aid 文章id
	 */
	public void deleteAttachmentsByAid(String aid);
	
}
