package top.duyt.service;

import java.util.List;

import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;

public interface ICategoryService {
	
	/**
	 * 新增一个分类栏目
	 * @param c 分类
	 * @param pCid 分类父ID
	 * @return
	 */
	public Category addCate(Category c,Integer pCid);
	
	
	/**
	 * 删除一个栏目
	 * @param id
	 */
	public void deleteCate(int id);
	
	/**
	 * 更新一个栏目
	 * @param c
	 */
	public void updateCate(Category c);
	
	/**
	 * 获取一个栏目
	 * @param id 
	 * @return
	 */
	public Category loadCategory(int id);
	
	/**
	 * 查询指定父栏目下的最大排序编号
	 * @param pCid 父栏目id
	 * @return
	 */
	public Integer findMaxOrdersUnderParent(Integer pCid);
	
	/**
	 * 查询指定父栏目下的所有子栏目
	 * @param pCid
	 * @return
	 */
	public List<Category> listCategoryByPcid(Integer pCid);
	
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
	
	/**
	 * 获取所有的非导航栏目（意为：可以含有内容的栏目）
	 * @return
	 */
	public List<Category> listAllCateExceptNavCate();

}
