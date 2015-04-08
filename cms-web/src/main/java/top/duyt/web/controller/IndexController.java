package top.duyt.web.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.duyt.model.Article;
import top.duyt.model.Category;
import top.duyt.model.IndexImg;
import top.duyt.service.IArticleService;
import top.duyt.service.ICategoryService;
import top.duyt.service.IindexImgService;
import top.duyt.utils.PropertiesUtil;

@Controller
@RequestMapping("/")
public class IndexController {

	private IindexImgService indexImgService;
	private ICategoryService categoryService;
	private IArticleService articleService;

	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(Model model) {
		//获取首页滚动图
		List<IndexImg> iis = indexImgService.listIndexImgsBySort();
		model.addAttribute("iis", iis);

		//获取最新添加的十篇文章
		List<Article> as = articleService.findLatest(10);
		model.addAttribute("latestArts", as);
		
		//首页导航栏只获取技术栏目
		List<Category> techs =  categoryService.listCategoryByPcid(10001);
		model.addAttribute("techCates", techs);
		
		return "index/index";
	}
	
	/**
	 * 后台管理入口
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adx")
	public String adx(Model model) {
		return "redirect:/user/users";
	}

	/**
	 * 个人简历
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/profile")
	public String profile(Model model) {
		try {
			PropertiesUtil pu = PropertiesUtil.getInstance("websiteInfo.properties");
			model.addAttribute("profile",pu.getProps("profile"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index/profile";
	}
	
	/**
	 * 获取简历
	 * @return
	 */
	@RequestMapping(value = "/getPfl")
	public void getBody(@Param String site,HttpServletResponse resp,HttpServletRequest req){
		
		String rel = "0";
		try {
			PropertiesUtil pu = PropertiesUtil.getInstance("websiteInfo.properties");
			String invitedSite = pu.getProps("invitedSite");
			List<String> invitedSites = Arrays.asList(invitedSite.split(","));
			
			//检验当前网址是否受邀请
			if(invitedSites.contains(site)){
				rel = pu.getProps("profile");
				rel = rel.replace("doj398vhqp0v39c", req.getContextPath()+"/resources/profileFavicon.jpg");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().print(rel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public IindexImgService getIndexImgService() {
		return indexImgService;
	}

	@Inject
	public void setIndexImgService(IindexImgService indexImgService) {
		this.indexImgService = indexImgService;
	}

	public ICategoryService getCategoryService() {
		return categoryService;
	}

	@Inject
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public IArticleService getArticleService() {
		return articleService;
	}

	@Inject
	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}

}
