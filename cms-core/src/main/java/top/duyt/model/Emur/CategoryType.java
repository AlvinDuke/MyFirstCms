package top.duyt.model.Emur;

/**
 * 栏目类型枚举
 * @author Alvin Du
 *
 */
public enum CategoryType {
	
	NAV_CATE("导航栏目"),
	CONTENT_CATE("新闻栏目"),
	LIST_CATE("列表栏目"),
	IMG_CATE("图集栏目");

	private String name;

	private CategoryType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
