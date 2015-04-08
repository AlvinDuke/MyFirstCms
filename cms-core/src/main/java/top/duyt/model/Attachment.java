package top.duyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_attachment")
public class Attachment {

	private int id;

	/**
	 * 原始文件名
	 */
	private String oriName;

	/**
	 * 新文件名
	 */
	private String newName;

	/**
	 * 附件类型
	 */
	private String type;

	/**
	 * 附件大小
	 */
	private long size;

	/**
	 * 访问本文件的url
	 */
	private String accessUrl;

	/**
	 * 文件拓展名
	 */
	private String extention;

	/**
	 * 附件所属文章
	 */
	private Article article;

	public Attachment() {

	}

	public Attachment(int id, String oriName, String newName, String type,
			long size, String accessUrl, String extention, int aid) {
		this.id = id;
		this.oriName = oriName;
		this.newName = newName;
		this.type = type;
		this.size = size;
		this.accessUrl = accessUrl;
		this.extention = extention;
		this.article.setId(aid);
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@ManyToOne
	@JoinColumn(name = "a_id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Column(name = "access_url")
	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String url) {
		this.accessUrl = url;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

}
