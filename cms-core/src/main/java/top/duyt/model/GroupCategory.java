package top.duyt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_group_category")
public class GroupCategory {

	private int id;
	private Group group;
	private Category category;

	public GroupCategory() {
		super();
	}

	public GroupCategory(int id, Group group, Category category) {
		this.id = id;
		this.group = group;
		this.category = category;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "g_id")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ManyToOne
	@JoinColumn(name = "c_id")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

}
