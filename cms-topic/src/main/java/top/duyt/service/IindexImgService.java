package top.duyt.service;

import java.util.List;

import top.duyt.model.IndexImg;

public interface IindexImgService {
	
	/**
	 * 新增一条首页欢迎图信息
	 * @param ii
	 * @return
	 */
	public IndexImg addIndexImg(IndexImg ii);
	
	/**
	 * 删除一条首页欢迎图
	 * @param id
	 */
	public void deleteIndexImg(int id);
	
	/**
	 * 列表出所有的首页欢迎图信息
	 * @return
	 */
	public List<IndexImg> listAllIndexImgs();
	
	/**
	 * 按照排序获取在首页展示的图片
	 * @return
	 */
	public List<IndexImg> listIndexImgsBySort();

	/**
	 * 根据id查找一条首页欢迎信息
	 * @param id
	 * @return
	 */
	public IndexImg load(int id);
	
	/**
	 * 更新一则首页欢迎信息
	 * @param img
	 */
	public void updateIndexImg(IndexImg img);
	
	/**
	 * 更新图片顺序
	 */
	public void updateOrders(String[] iids);
	
	/**
	 * 获取最大的排序
	 * @return
	 */
	public Integer findMaxSort();
	
}
