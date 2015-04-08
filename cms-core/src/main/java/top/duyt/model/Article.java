package top.duyt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_article")
public class Article {

	private int id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 冗余字段：便于直接获取文章作者
	 */
	private String author;

	/**
	 * 冗余字段：便于直接获取文章所属栏目
	 */
	private String cateName;

	/**
	 * 是否推荐
	 */
	private int isrecommend;

	/**
	 * 文章状态
	 */
	private int status;

	/**
	 * 是否是转发的文章
	 */
	private int isRepost;

	/**
	 * 阅读次数
	 */
	private int readCount;

	/**
	 * 创建时间
	 */
	private Date creDate;

	/**
	 * 发布时间
	 */
	private Date publishDate;

	/**
	 * 文章关联的用户（作者）
	 */
	private User user;

	/**
	 * 文章所属的栏目
	 */
	private Category category;

	/**
	 * 检索文章的关键字
	 */
	private String keyword;

	/**
	 * 文章摘要
	 */
	private String summary;

	/**
	 * 文章内容
	 */
	private String content;

	public Article() {

	}

	/**
	 * 列表页用的构造器，无需全部属性
	 * 
	 * @param id
	 * @param title
	 * @param author
	 * @param cateName
	 * @param isrecommend
	 * @param status
	 * @param creDate
	 * @param publishDate
	 */
	public Article(int id, String title, String author, String cateName,
			int isrecommend, int status, Date creDate, Date publishDate,
			String summary, int isRepost) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.cateName = cateName;
		this.isrecommend = isrecommend;
		this.status = status;
		this.creDate = creDate;
		this.publishDate = publishDate;
		this.summary = summary;
		this.isRepost = isRepost;
	}

	public Article(int id, String title, String author, String cateName,
			int isrecommend, int status, int isRepost, int readCount,
			Date creDate, Date publishDate, User user, Category category,
			String keyword, String summary, String content) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.cateName = cateName;
		this.isrecommend = isrecommend;
		this.status = status;
		this.isRepost = isRepost;
		this.readCount = readCount;
		this.creDate = creDate;
		this.publishDate = publishDate;
		this.user = user;
		this.category = category;
		this.keyword = keyword;
		this.summary = summary;
		this.content = content;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getIsrecommend() {
		return isrecommend;
	}

	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "cre_date")
	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	@Column(name = "public_date")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@ManyToOne
	@JoinColumn(name = "u_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "c_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsRepost() {
		return isRepost;
	}

	public void setIsRepost(int isRepost) {
		this.isRepost = isRepost;
	}

	@Column(name = "read_count")
	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

}
