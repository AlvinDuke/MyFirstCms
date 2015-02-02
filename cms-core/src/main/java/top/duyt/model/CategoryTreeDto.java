package top.duyt.model;

/**
 * 栏目列表，栏目树数据传送对象
 * 
 * @author Alvin Du
 * 
 */
public class CategoryTreeDto {

	private int id;
	private String name;
	private int orders;
	private Integer pId;

	public CategoryTreeDto() {
		super();
	}

	public CategoryTreeDto(int id, String name, int orders, Integer pId) {
		this.id = id;
		this.name = name;
		this.orders = orders;
		this.pId = pId;
	}

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

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

}
