package top.duyt.dao;

import java.util.List;

import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;

import top.duyt.dao.IbaseDao;

public interface ICategoryDao extends IbaseDao<Category> {

	/**
	 * 查找父类目包含的所有子类目
	 * 
	 * @param pCid
	 * @return
	 */
	public List<Category> listCateByPcid(Integer pCid);

	/**
	 * 取得指定父类目下，排序字段的最大值
	 * 
	 * @return
	 */
	public Integer findMaxOrdersUnderParent(Integer pCid);

	/**
	 * 取得所有的目录树
	 * 
	 * @return
	 */
	public List<CategoryTreeDto> listAllCateTreeDto();
	
	/**
	 * 根据父目录取得所有子目录
	 * @param pid
	 * @return
	 */
	public List<CategoryTreeDto> listCateTreeDtoByPid(Integer pid);
	
	/**
	 * 更新一组栏目的排序，排序根据传入栏目id在数组中的顺序递增
	 * @param cids 指定一组栏目的id
	 */
	public void updateCategorysOrders(Integer[] cids);

}
