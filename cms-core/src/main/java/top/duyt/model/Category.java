package top.duyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import top.duyt.model.Emur.CategoryType;

@Entity
@Table(name = "t_category")
public class Category {

	private int id;
	private String name;
	private CategoryType categoryType;
	private int isCustom;
	private int orders;
	private String customLink;
	private int isNavCate;
	private int isListCate;
	private int isImgCate;
	private int isRecommended;
	private int status;
	private Category category;

	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "category_type")
	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public int getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(int isCustom) {
		this.isCustom = isCustom;
	}

	public String getCustomLink() {
		return customLink;
	}

	public void setCustomLink(String customLink) {
		this.customLink = customLink;
	}

	public int getIsNavCate() {
		return isNavCate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ManyToOne
	@OneToMany(mappedBy = "id")
	@JoinColumn(name = "p_cid")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(int isRecommended) {
		this.isRecommended = isRecommended;
	}

	public int getIsListCate() {
		return isListCate;
	}

	public void setIsListCate(int isListCate) {
		this.isListCate = isListCate;
	}

	public int getIsImgCate() {
		return isImgCate;
	}

	public void setIsImgCate(int isImgCate) {
		this.isImgCate = isImgCate;
	}

	public void setIsNavCate(int isNavCate) {
		this.isNavCate = isNavCate;
	}

}
