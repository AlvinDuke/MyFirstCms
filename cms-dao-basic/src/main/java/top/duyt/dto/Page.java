package top.duyt.dto;

import java.util.List;

/**
 * 分页对象
 * @author Alvin Du
 *
 * @param <T>
 */
public class Page<T> {

	/**
	 * 分页大小
	 */
	private int pageSize;
	/**
	 * 起始记录下标
	 */
	private int offSet;
	/**
	 * 总记录数
	 */
	private long totalRecords;
	/**
	 * 总页数
	 */
	private long totalPages;
	/**
	 * 当前分页的实际数据内容
	 */
	private List<T> datas;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

}
