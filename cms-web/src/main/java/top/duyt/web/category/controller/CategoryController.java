package top.duyt.web.category.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.logging.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Emur.CategoryType;
import top.duyt.service.ICategoryService;
import top.duyt.utils.EnumUtil;
import top.duyt.utils.JSONUtil;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private ICategoryService categoryService;

	/**
	 * 初始化栏目树
	 * @param model
	 */
	private void initTree(Model model){
		List<CategoryTreeDto> cts = categoryService.listAllCateTreeDto();
		model.addAttribute("treeData",JSONUtil.bean2JSON(cts));
	}
	
	@RequestMapping("/categorys")
	public String listCates(Model model) {
		return "redirect:/category/subCates/0";
	}
	
	@RequestMapping("/subCates/{pId}")
	public String listSubCates(@PathVariable Integer pId,Model model){
		initTree(model);
		//获取当前栏目
		Category c = null;
		if(pId == null||pId == 0){
			c = new Category();
			c.setId(0);
			c.setName("信息管理系统栏目管理");
		}else{
			c = categoryService.loadCategory(pId);
		}
		model.addAttribute("c", c);
		//获取子栏目
		List<Category> cs = categoryService.listCategoryByPcid(pId);
		model.addAttribute("cs", cs);
		return "category/categoryList";
	}
	
	/**
	 * 新增跳转
	 * @param pId
	 * @param model
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add/{pId}",method = RequestMethod.GET)
	public String addCategory(@PathVariable Integer pId,Model model) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException{
		initTree(model);
		//当前的父栏目
		Category c = categoryService.loadCategory(pId);
		//栏目类型
		Map<String , Object> types =  EnumUtil.enumProp2MapThrouEnumName(CategoryType.class, "name");
		model.addAttribute("types", types);
		model.addAttribute("c", c);
		
		model.addAttribute("cate", new Category());
		return "category/categoryAddInput";
	}
	
	/**
	 * 添加栏目
	 * @param c
	 * @param br
	 * @return
	 */
	@RequestMapping(value = "/add/*",method = RequestMethod.POST)
	public String addCategory(@Validated Category c,BindingResult br){
		if (!br.hasErrors()) {
			return "category/categoryAddInput";
		}
		categoryService.addCate(c, c.getCategory().getId());
		return "redirect:/category/subCates/0";
	}
	
	/**
	 * 栏目更新跳转
	 * @param cid
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update/{cid}",method = RequestMethod.GET)
	public String updateCategory(@PathVariable Integer cid,
								 Model model) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException{
		initTree(model);
		Category c = categoryService.loadCategory(cid);
		model.addAttribute("c", c);
		
		//栏目类型
		Map<String , Object> types =  EnumUtil.enumProp2MapThrouEnumName(CategoryType.class, "name");
		model.addAttribute("types", types);
		
		return "category/categoryUpdateInput";
	}
	
	/**
	 * 更新栏目
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/update/*",method = RequestMethod.POST)
	public String updateCategory(@ModelAttribute Category c,
								BindingResult br){
		if (br.hasErrors()) {
			return "category/categoryUpdateInput";
		}
		
		Category cDb = categoryService.loadCategory(c.getId());
		if (cDb != null) {
			cDb.setCategoryType(c.getCategoryType());
			cDb.setCustomLink(c.getCustomLink());
			cDb.setIsCustom(c.getIsCustom());
			cDb.setIsImgCate(c.getIsImgCate());
			cDb.setIsListCate(c.getIsListCate());
			cDb.setIsNavCate(c.getIsNavCate());
			cDb.setIsRecommended(c.getIsRecommended());
			cDb.setName(c.getName());
			cDb.setStatus(c.getStatus());
			categoryService.updateCate(cDb);
		}
		return "redirect:/category/subCates/0";
	}
	
	/**
	 * 删除一个栏目
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/delete/{cid}")
	public String deleteCategory(@PathVariable Integer cid){
		categoryService.deleteCate(cid);
		return "redirect:/category/subCates/0";
	}
	
	/**
	 * 更新栏目排序
	 * @param cids
	 * @return
	 */
	@RequestMapping(value = "/updateOrders")
	public @ResponseBody String updateOrders(@Param Integer[] cids){
		try {
			categoryService.updateCategorysOrders(cids);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
