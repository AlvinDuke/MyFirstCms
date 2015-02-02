package top.duyt.web.user.dto;

import java.util.List;

import top.duyt.model.User;

/**
 * 仅用于界面层，用户添加时的数据传输
 * 
 * @author Alvin Du
 * 
 */
public class UserDto {

	private User user;
	private List<Integer> groupIds;
	private List<Integer> roleIds;

	public UserDto() {
		super();
	}

	public UserDto(User user, List<Integer> groupIds, List<Integer> roleIds) {
		this.user = user;
		this.groupIds = groupIds;
		this.roleIds = roleIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Integer> groupIds) {
		this.groupIds = groupIds;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

}
