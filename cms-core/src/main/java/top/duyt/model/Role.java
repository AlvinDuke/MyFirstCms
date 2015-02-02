package top.duyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import top.duyt.model.Emur.RoleType;

@Entity
@Table(name = "t_role")
public class Role {

	private int id;
	private String roleName;
	private String roleNum;
	private RoleType roleType;

	public Role() {
		super();
	}

	public Role(int id, String roleName, String roleNum, RoleType roleType) {
		this.id = id;
		this.roleName = roleName;
		this.roleNum = roleNum;
		this.roleType = roleType;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="role_type")
	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

}
