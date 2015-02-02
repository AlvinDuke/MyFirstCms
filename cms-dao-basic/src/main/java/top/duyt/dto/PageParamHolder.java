package top.duyt.dto;

/**
 * 分页参数传输对象
 * @author Alvin Du
 *
 */
public class PageParamHolder {

	/**
	 * 分页大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 起始记录下标
	 */
	private static ThreadLocal<Integer> offSet = new ThreadLocal<Integer>();
	/**
	 * 排序目标
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	/**
	 * 排序升降
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();

	public static void removeAll(){
		removeOffSet();
		removePageSize();
		removeSort();
		removeOrder();
	}
	
	public static Integer getPageSize() {
		return pageSize.get();
	}

	public static void setPageSize(Integer _pageSize) {
		pageSize.set(_pageSize);
	}

	public static Integer getOffSet() {
		return offSet.get();
	}

	public static void setOffSet(Integer _offSet) {
		offSet.set(_offSet);
	}

	public static String getSort() {
		return sort.get();
	}

	public static void setSort(String _sort) {
		sort.set(_sort);
	}

	public static String getOrder() {
		return order.get();
	}

	public static void setOrder(String _order) {
		order.set(_order);
	}
	
	public static void removeOffSet(){
		offSet.remove();
	}
	
	public static void removePageSize(){
		pageSize.remove();
	}
	
	public static void removeSort(){
		sort.remove();
	}
	
	public static void removeOrder(){
		order.remove();
	}

}
