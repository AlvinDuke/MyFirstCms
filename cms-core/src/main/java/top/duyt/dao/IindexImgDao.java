package top.duyt.dao;

import java.util.List;

import top.duyt.model.IndexImg;

public interface IindexImgDao extends IbaseDao<IndexImg>{

	/**
	 * 列表出所有的首页信息
	 * @return
	 */
	public List<IndexImg> listAllIndexImgs();
	
	/**
	 * 重置排序信息
	 */
	public void resetOrders();
	
	/**
	 * 按照排序获取在首页展示的图片
	 * @return
	 */
	public List<IndexImg> listIndexImgsBySort();
	
	/**
	 * 获取最大的排序
	 * @return
	 */
	public Integer findMaxSort();
}
