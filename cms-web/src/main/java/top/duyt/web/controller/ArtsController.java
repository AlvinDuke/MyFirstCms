package top.duyt.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import top.duyt.dto.Page;
import top.duyt.model.Article;
import top.duyt.model.Attachment;
import top.duyt.model.Category;
import top.duyt.model.CategoryTreeDto;
import top.duyt.model.Keyword;
import top.duyt.model.User;
import top.duyt.service.IArticleService;
import top.duyt.service.IAttachmentService;
import top.duyt.service.ICategoryService;
import top.duyt.service.IKeywordService;
import top.duyt.utils.JSONUtil;
import top.duyt.utils.Pinyin4jUtil;
import top.duyt.utils.PropertiesUtil;
import top.duyt.web.user.dto.ArticleDto;

/**
 * 后台文章管理
 * @author Alvin Du
 *
 */
@Controller
@RequestMapping("/arts")
public class ArtsController {

	private IArticleService articleService;
	private ICategoryService categoryService;
	private IAttachmentService attachmentService;
	private IKeywordService keywordService;

	PropertiesUtil pu = null;
	
	public ArtsController() {
		try {
			pu = PropertiesUtil.getInstance("websiteInfo.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询栏目下的文章
	 * @param req
	 * @param keyword
	 * @param model
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/{cid}")
	public String listArticles(HttpServletRequest req,String condition,String dateCon,Model model,@PathVariable Integer cid) {
		//获取当前栏目
		Category c = categoryService.loadCategory(cid);
		model.addAttribute("c",c);
		//获取栏目包含的文章信息（只获取已经发布的文章）
		//是否是获取全部栏目
		Page<Article> as = articleService.find(cid, condition, 1,dateCon);
		model.addAttribute("articles",as);
		
		//导航栏
		if(req.getServletContext().getAttribute("navCates")==null){
			List<CategoryTreeDto> ctds = categoryService.listAllCateTreeDto();
			Map<Object, Object> cates = new LinkedHashMap<Object, Object>();
			for (CategoryTreeDto ctd : ctds) {
				//不是根目录
				if (ctd.getId()!=9999) {
					//如果是导航栏就直接添加为key
					if(ctd.getIsNavCate() == 1){
						List<CategoryTreeDto> subCates = categoryService.listCateTreeDtoByPid(ctd.getId());
						cates.put(ctd, subCates);
					}
				}
			}
			req.getServletContext().setAttribute("navCates", cates);
		}
		
		//文章按月份列出每月的数量
		if(req.getServletContext().getAttribute("artsGroupByMonth")==null){
			List<String[]> dateGroupByMonth = articleService.findArtsCountsGroupByMonth();
			req.getServletContext().setAttribute("artsGroupByMonth", dateGroupByMonth);
		}
		
		//关于信息
		model.addAttribute("about",pu.getProps("about"));
		
		//保存查询条件
		model.addAttribute("condition", condition);
		model.addAttribute("dateCon", dateCon);
		
		return "arts/arts";
	}
	
	/**
	 * 查看文章详情
	 * @param req
	 * @param model
	 * @param aid
	 * @return
	 */
	@RequestMapping(value = "/show/{aid}")
	public String showArticle(HttpServletRequest req,Model model,@PathVariable Integer aid) {
		Article a = articleService.loadArticle(aid);
		model.addAttribute("a",a);
		
		//阅读次数+1，作弊啊。。
		a.setReadCount(a.getReadCount()+1);
		articleService.updateArticle(a);
		
		//导航栏
		if(req.getServletContext().getAttribute("navCates")==null){
			List<CategoryTreeDto> ctds = categoryService.listAllCateTreeDto();
			Map<Object, Object> cates = new LinkedHashMap<Object, Object>();
			for (CategoryTreeDto ctd : ctds) {
				//不是根目录
				if (ctd.getId()!=9999) {
					//如果是导航栏就直接添加为key
					if(ctd.getIsNavCate() == 1){
						List<CategoryTreeDto> subCates = categoryService.listCateTreeDtoByPid(ctd.getId());
						cates.put(ctd, subCates);
					}
				}
			}
			req.getServletContext().setAttribute("navCates", cates);
		}
		
		//附件信息
		//获取附件对象
		List<Attachment> atts = attachmentService.listAttachmentsByAid(aid.toString());
		model.addAttribute("attachs", atts);
		
		//获取当前栏目
		model.addAttribute("c",a.getCategory());
		
		//文章按月份列出每月的数量
		if(req.getServletContext().getAttribute("artsGroupByMonth")==null){
			List<String[]> dateGroupByMonth = articleService.findArtsCountsGroupByMonth();
			req.getServletContext().setAttribute("artsGroupByMonth", dateGroupByMonth);
		}
		
		//关于信息
		model.addAttribute("about",pu.getProps("about"));
		return "arts/artShow";
	}

	public IArticleService getArticleService() {
		return articleService;
	}

	@Inject
	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public IKeywordService getKeywordService() {
		return keywordService;
	}

	@Inject
	public void setKeywordService(IKeywordService keywordService) {
		this.keywordService = keywordService;
	}

}
