package top.duyt.web.user.dto;

import top.duyt.model.Article;

public class ArticleDto {

	/**
	 * 文章
	 */
	private Article article;

	/**
	 * 创建日期
	 */
	private String creDate;

	/**
	 * 关键字
	 */
	private String keywords;

	/**
	 * 栏目id
	 */
	private String cid;

	/**
	 * 附件id
	 */
	private String attachIds;

	/**
	 * 更新时，存储文章的id
	 */
	private String artId;

	public ArticleDto() {
	}

	public ArticleDto(Article article, String creDate, String keywords,
			String cid, String attachIds, String artId) {
		this.article = article;
		this.creDate = creDate;
		this.keywords = keywords;
		this.cid = cid;
		this.attachIds = attachIds;
		this.artId = artId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getArtId() {
		return artId;
	}

	public void setArtId(String artId) {
		this.artId = artId;
	}

}
