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
	private boolean chkDisabled;
	private boolean checked;
	private int isNavCate;

	public CategoryTreeDto() {
		super();
	}

	public CategoryTreeDto(int id, String name, int orders, Integer pId,
			boolean chkDisabled, boolean checked, int isNavCate) {
		this.id = id;
		this.name = name;
		this.orders = orders;
		this.pId = pId;
		this.chkDisabled = chkDisabled;
		this.checked = checked;
		this.isNavCate = isNavCate;
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

	public boolean isChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getIsNavCate() {
		return isNavCate;
	}

	public void setIsNavCate(int isNavCate) {
		this.isNavCate = isNavCate;
	}

}
