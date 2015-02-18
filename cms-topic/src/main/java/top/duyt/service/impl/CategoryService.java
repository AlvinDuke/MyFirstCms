package top.duyt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import top.duyt.Exception.CmsException;
import top.duyt.dao.ICategoryDao;
import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.service.ICategoryService;

/**
 * 栏目业务实现
 * 
 * @author Alvin Du
 * 
 */
@Service("categoryService")
public class CategoryService implements ICategoryService {

	private ICategoryDao categoryDao;

	public Category addCate(Category c, Integer pCid) {
		Category cTemp = categoryDao.load(pCid);
		if (cTemp == null) {
			throw new CmsException("要添加的父栏目不存在");
		}
		else{
			c.setCategory(cTemp);
			categoryDao.add(c);
			return c;
		}
	}

	public void deleteCate(int id) {
		//栏目含有子分类，不能删除
		List<Category> cs = categoryDao.listCateByPcid(id);
		if (cs.size()>0) {
			throw new CmsException("要删除的栏目含有子栏目，不可以删除");
		}
		//TODO 是否含有文章
		
		//TODO 删除和用户组的关系
		categoryDao.delete(id);
	}
	
	@Override
	public List<CategoryTreeDto> listAllCateTreeDto() {
		List<CategoryTreeDto> cateAllList = categoryDao.listAllCateTreeDto();
		return cateAllList;
	}

	@Override
	public List<CategoryTreeDto> listCateTreeDtoByPid(Integer pid) {
		return listCateTreeDtoByPid(pid);
	}
	
	@Override
	public void updateCategorysOrders(Integer[] cids) {
		categoryDao.updateCategorysOrders(cids);
	}

	public void updateCate(Category c) {
		categoryDao.update(c);
	}

	public Category loadCategory(int id) {
		return categoryDao.load(id);
	}

	public Integer findMaxOrdersUnderParent(Integer pCid) {
		return categoryDao.findMaxOrdersUnderParent(pCid);
	}

	public List<Category> listCategoryByPcid(Integer pCid) {
		return categoryDao.listCateByPcid(pCid);
	}

	public ICategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Inject
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}
