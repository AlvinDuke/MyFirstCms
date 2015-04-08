package top.duyt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_index_img")
public class IndexImg {

	private int id;
	private String mainTitle;
	private String subTitle;
	private String link;
	private long indexImgSize;
	private String oriName;
	private String newName;
	private Date creDate;
	private String extention;
	private String accessUrl;
	private long width;
	private long height;
	private int sortNum;

	public IndexImg() {
	}

	public IndexImg(int id, String mainTitle, String subTitle, String link,
			long indexImgSize, String oriName, String newName, Date creDate,
			String extention, String accessUrl, long width, long height,
			int sortNum) {
		this.id = id;
		this.mainTitle = mainTitle;
		this.subTitle = subTitle;
		this.link = link;
		this.indexImgSize = indexImgSize;
		this.oriName = oriName;
		this.newName = newName;
		this.creDate = creDate;
		this.extention = extention;
		this.accessUrl = accessUrl;
		this.width = width;
		this.height = height;
		this.sortNum = sortNum;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "main_title")
	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	@Column(name = "sub_title")
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "index_img_size")
	public long getIndexImgSize() {
		return indexImgSize;
	}

	public void setIndexImgSize(long indexImgSize) {
		this.indexImgSize = indexImgSize;
	}

	@Column(name = "ori_name")
	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	@Column(name = "new_name")
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Column(name = "cre_date")
	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}
