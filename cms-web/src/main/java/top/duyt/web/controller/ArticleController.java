package top.duyt.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
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
import top.duyt.web.user.dto.ArticleDto;

/**
 * 后台文章管理
 * @author Alvin Du
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	private IArticleService articleService;
	private ICategoryService categoryService;
	private IAttachmentService attachmentService;
	private IKeywordService keywordService;

	/**
	 * 初始化栏目树
	 */
	private void initCateTree(Model model){
		// 获取栏目的数据
		List<CategoryTreeDto> cts = categoryService.listAllCateTreeDto();
		model.addAttribute("treeData", JSONUtil.bean2JSON(cts));
	}
	
	/**
	 * 保存关键字
	 * @param keywordsSrc
	 */
	private void saveKeywords(String keywordsSrc){
		Set<String> keywords = new HashSet<>();
		keywords.addAll(Arrays.asList(keywordsSrc.split("\\|")));
		Keyword k=null;
		for (String key : keywords) {
			k = new Keyword();
			k.setKeyword(key);
			k.setKwShortPy(Pinyin4jUtil.converterToFirstSpell(key));
			k.setKwFullPy(Pinyin4jUtil.converterToSpell(key));
			keywordService.addKeyword(k);
		}
	}
	
	@RequestMapping(value = "/articles")
	public String listArticles(HttpServletRequest req, Model model,
			String condition, Integer cid, Integer status) {
		// 是否是管理员
		boolean isAdmin = (boolean) req.getSession().getAttribute("isAdmin");
		Page<Article> articles = null;
		if (isAdmin) {
			articles = articleService.find(cid, condition, status);
		} else {
			articles = articleService
					.find(((User) (req.getSession().getAttribute("isAdmin")))
							.getId(), cid, condition, status);
		}

		// 栏目列表
		List<Category> as = categoryService.listAllCateExceptNavCate();
		model.addAttribute("categorys", as);
		// 文章列表数据
		model.addAttribute("articles", articles);
		// 查询条件
		model.addAttribute("condition", condition);
		model.addAttribute("cid", cid);
		model.addAttribute("status", status);

		return "article/articleList";
	}

	@RequestMapping(value = "/addInput", method = RequestMethod.GET)
	public String articlesAddInput(Model model) {
		initCateTree(model);
		return "article/articleAddInput";
	}

	@RequestMapping(value = "/delete/{aid}")
	public String articleDelete(@PathVariable String aid){
		articleService.deleteArticle(Integer.valueOf(aid));
		return "redirect:/article/articles";
	}
	
	@RequestMapping(value = "/addInput", method = RequestMethod.POST)
	public String articleAdd(@ModelAttribute ArticleDto adto,
			HttpServletRequest req) throws ParseException {
		//文章按月份分组
		req.getServletContext().removeAttribute("artsGroupByMonth");
		// 登录用户
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		// 关键字处理
		adto.getArticle().setKeyword(adto.getKeywords());
		// 保存关键字
		saveKeywords(adto.getKeywords());
		//处理创建时间
		if(adto.getCreDate()!=null && !"".equals(adto.getCreDate())){
			String newCreDate = adto.getCreDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			adto.getArticle().setCreDate(sdf.parse(newCreDate));
		}else{
			adto.getArticle().setCreDate(new Date());
		}
		//处理发布时间
		if(adto.getArticle().getStatus() == 1){
			adto.getArticle().setPublishDate(new Date());
		}
		// 附件
		String attachIds = adto.getAttachIds();
		// 用户上传了附件
		if (attachIds != null && !"".equals(attachIds)) {
			articleService.addArticle(adto.getArticle(), Integer.valueOf(adto
					.getCid()), loginUser.getId(),
					attachIds.split(","));
		} else {
			// 未上传附件
			articleService.addArticle(adto.getArticle(),
					Integer.valueOf(adto.getCid()), loginUser.getId());
		}

		return "redirect:/article/articles";
	}
	
	@RequestMapping(value = "/updateInput/{aid}", method = RequestMethod.GET)
	public String updateArticle(Model model,@PathVariable String aid){
		//获取栏目的数据
		initCateTree(model);
		//获取文章对象
		Article a = articleService.loadArticle(Integer.valueOf(aid));
		model.addAttribute("art", a);
		//获取附件对象
		List<Attachment> atts = attachmentService.listAttachmentsByAid(aid);
		model.addAttribute("attachs", atts);
		return "article/articleAddInput";
	}
	
	@RequestMapping(value = "/updateInput/{aid}", method = RequestMethod.POST)
	public String updateArticle(@ModelAttribute ArticleDto adto,
								HttpServletRequest req){
		
		//获取原文章
		Article dbArt = articleService.loadArticle(Integer.valueOf(adto.getArtId()));
		//原文章id
		int oriArtId = dbArt.getId();
		//更新后的对象
		Article newArt = adto.getArticle();
		//设置标题
		dbArt.setTitle(newArt.getTitle());
		//变更后的栏目id
		int newCid = Integer.valueOf(adto.getCid());
		//判断所属栏目是否变化
		if(dbArt.getCategory().getId()!=newCid){
			//有变化就更新栏目，设置栏目
			Category tempCate = new Category();
			tempCate.setId(newCid); 
			dbArt.setCategory(tempCate);
		}
		dbArt.setSummary(newArt.getSummary());
		dbArt.setKeyword(adto.getKeywords());
		//关键字逐个保存
		saveKeywords(adto.getKeywords());
		dbArt.setContent(newArt.getContent());
		
		//判断更新后的附件和原附件的差异
		//更新后的附件id
		String[] newAttachIds = adto.getAttachIds().split(",");
		//原文章包含的所有附件
		List<String> oriAttachIds = attachmentService.listAttachmentIdsByAid(String.valueOf(oriArtId));
		//新附件是否存在
		boolean isThisAttExist = false;
		for (String newAttId : newAttachIds) {
			if(newAttId==null || newAttId.equals("")) continue;
			//原文章的附件包含新文章的附件，则说明该文件无需变化
			if(oriAttachIds.indexOf(newAttId)!=-1){
				isThisAttExist = true;
			}
			//不包含则为文章添加这则新附件
			if(!isThisAttExist){
				attachmentService.updateAttachmentToArticle(String.valueOf(oriArtId),newAttId); 
			}
		}
		dbArt.setIsrecommend(newArt.getIsrecommend());
		dbArt.setIsRepost(newArt.getIsRepost());
		dbArt.setStatus(newArt.getStatus());
		articleService.updateArticle(dbArt);
		
		return "redirect:/article/articles";
	}

	/**
	 * 文件上传
	 * 
	 * @param attach
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping("/uploadify")
	public void uploadify(MultipartFile attach,
			MultipartHttpServletRequest mreq, HttpServletResponse resp)
			{
		// 最大文件大小
		long maxSize = 10000000;

		resp.setContentType("text/plain; charset=UTF-8");

		// 定义允许上传的文件扩展名
		Map<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 文件保存目录路径
		String savePath = mreq.getServletContext().getRealPath("/");
		// 文件url
		String saveUrl = mreq.getContextPath();

		// 上传文章附件
		try {
			if (attach != null) {
				savePath += File.separator + "attachments" + File.separator;
				saveUrl += File.separator + "attachments" + File.separator;
				// 创建当前日期的文件夹
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + File.separator;
				saveUrl += ymd + File.separator;
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}

				// 检查扩展名
				String fileExt = FilenameUtils.getExtension(
						attach.getOriginalFilename()).toLowerCase();
				// 新文件名
				String newFileName = new Date().getTime() + "." + fileExt;
				Attachment ach = new Attachment();
				ach.setAccessUrl(saveUrl + newFileName);
				ach.setExtention(fileExt);
				ach.setNewName(newFileName);
				ach.setOriName(attach.getOriginalFilename());
				ach.setSize(attach.getSize());
				ach.setType(attach.getContentType());

				// 新增附件信息记录
				attachmentService.addAttachment(ach, null);
				// 保存附件
				FileUtils.copyInputStreamToFile(attach.getInputStream(), new File(
						savePath + newFileName));

				resp.getWriter().print(new JSONObject(ach));
			} else {

				// 上传文章内容所需的文件
				String dirName = mreq.getParameter("dir");
				if (dirName == null) {
					dirName = "image";
				}

				savePath += File.separator + "upload" + File.separator + dirName + File.separator;
				saveUrl += File.separator + "upload" + File.separator + dirName + File.separator;

				// 不存在上传目录则新建
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}

				// 创建当前日期的文件夹
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String ymd = sdf.format(new Date());
				savePath += ymd + File.separator;
				saveUrl += ymd + File.separator;
				File dirFile = new File(savePath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}

				// 获取当前上传的文件
				MultiValueMap<String, MultipartFile> files = mreq.getMultiFileMap();
				for (String key : files.keySet()) {
					List<MultipartFile> mfiles = files.get(key);
					for (MultipartFile mpf : mfiles) {
						// 检查文件大小
						if (mpf.getSize() > maxSize) {
							resp.getWriter().println(getError("上传文件大小超过限制。"));
							return;
						}
						// 检查扩展名
						String fileExt = FilenameUtils.getExtension(
								mpf.getOriginalFilename()).toLowerCase();
						if (!Arrays.<String> asList(extMap.get(dirName).split(","))
								.contains(fileExt)) {
							resp.getWriter().println(
									getError("上传文件扩展名是不允许的扩展名。\n只允许"
											+ extMap.get(dirName) + "格式。"));
							return;
						}
						// 新文件名
						String newFileName = dirName + "_" + new Date().getTime()
								+ "." + fileExt;
						// 保存附件
						FileUtils.copyInputStreamToFile(mpf.getInputStream(),
								new File(savePath + newFileName));

						JSONObject obj = new JSONObject();
						obj.put("error", 0);
						obj.put("url", saveUrl + newFileName);
						resp.getWriter().println(obj.toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
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
