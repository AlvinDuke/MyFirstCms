package top.duyt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User {

	private int id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private String cellPhone;
	/**
	 * 用户状态：0为停用，1为启用
	 */
	private String status;
	private Date credate;

	public User() {
		super();
	}

	public User(int id, String username, String nickname, String password,
			String email, String cellPhone, String status,
			Date credate) {
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.cellPhone = cellPhone;
		this.status = status;
		this.credate = credate;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "cre_date")
	public Date getCredate() {
		return credate;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

}
