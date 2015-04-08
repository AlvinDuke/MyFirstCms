package top.duyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_keyword")
public class Keyword {

	private int id;

	/**
	 * 关键字
	 */
	private String keyword;

	/**
	 * 关键字拼音缩略
	 */
	private String kwShortPy;

	/**
	 * 关键字完整拼音
	 */
	private String kwFullPy;

	/**
	 * 搜索次数
	 */
	private int searchtime;

	public Keyword() {
		
	}

	public Keyword(int id, String keyword, String kwShortPy, String kwFullPy,
			int searchtime) {
		this.id = id;
		this.keyword = keyword;
		this.kwShortPy = kwShortPy;
		this.kwFullPy = kwFullPy;
		this.searchtime = searchtime;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "kw_short_py")
	public String getKwShortPy() {
		return kwShortPy;
	}

	public void setKwShortPy(String kwShortPy) {
		this.kwShortPy = kwShortPy;
	}

	@Column(name = "kw_full_py")
	public String getKwFullPy() {
		return kwFullPy;
	}

	public void setKwFullPy(String kwFullPy) {
		this.kwFullPy = kwFullPy;
	}

	@Column(name = "search_time")
	public int getSearchtime() {
		return searchtime;
	}

	public void setSearchtime(int searchtime) {
		this.searchtime = searchtime;
	}

}
