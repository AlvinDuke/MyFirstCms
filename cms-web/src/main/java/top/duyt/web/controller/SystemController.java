package top.duyt.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.wicket.markup.MarkupCache.ICache;
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

import freemarker.template.TemplateException;
import top.duyt.dto.Page;
import top.duyt.dto.PageParamHolder;
import top.duyt.model.Article;
import top.duyt.model.Attachment;
import top.duyt.model.Category;
import top.duyt.model.IndexImg;
import top.duyt.service.IArticleService;
import top.duyt.service.ICategoryService;
import top.duyt.service.IindexImgService;
import top.duyt.utils.FreeMarkerUtil;
import top.duyt.utils.PropertiesUtil;
import top.duyt.web.user.dto.IndexImgDto;

@Controller
@RequestMapping("/system")
public class SystemController {

	private IindexImgService indexImgService;

	@RequestMapping(value = "/websiteInfos")
	public String websiteInfos(Model model) {
		
		try {
			PropertiesUtil pu = PropertiesUtil.getInstance("websiteInfo.properties");
			model.addAttribute("about",pu.getProps("about"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "system/websiteInfos";
	}
	
	@RequestMapping(value = "/indexImgs")
	public String indexImgs(Model model) {
		// 首页滚动导航
		List<IndexImg> iis = indexImgService.listAllIndexImgs();
		model.addAttribute("indexImgs", iis);

		return "system/indexImgList";
	}

	@RequestMapping(value = "/indexImgInput", method = RequestMethod.GET)
	public String indexImgAddInput() {
		return "system/indexImgInput";
	}

	@RequestMapping(value = "/delete/{iid}")
	public String indexImgDelete(@PathVariable int iid) {
		indexImgService.deleteIndexImg(iid);
		return "redirect:/system/indexImgs";
	}

	@RequestMapping(value = "/indexImgInput", method = RequestMethod.POST)
	public String indexImgAdd(@ModelAttribute IndexImgDto iiDto)
			throws IOException {
		// 保存首页信息
		IndexImg ii = indexImgService.load(iiDto.getImgId());
		ii.setMainTitle(iiDto.getMainTitle());
		ii.setSubTitle(iiDto.getSubTitle());
		ii.setLink(iiDto.getLink());
		indexImgService.updateIndexImg(ii);

		// 根据剪裁的选框，切割已保存的欢迎图片
		// 剪切后的y坐标
		int y = iiDto.getCropedY();
		String accessUrl = ii.getAccessUrl();
		accessUrl = accessUrl.replace(PageParamHolder.getContextPath(), "");

		// 源文件路径
		String srcFile = PageParamHolder.getRootPath() + accessUrl
				+ ii.getNewName();
		// 输出缩略图
		Thumbnails
				.of(srcFile)
				.sourceRegion(0, y, 1920, 500)
				.size(1920, 500)
				.keepAspectRatio(false)
				.toFile(srcFile.replace(ii.getNewName(),
						"thumbnail_" + ii.getNewName()));
		// 输出预览图
		Thumbnails
				.of(srcFile)
				.sourceRegion(0, y, 1920, 500)
				.size(384, 100)
				.keepAspectRatio(false)
				.toFile(srcFile.replace(ii.getNewName(),
						"preview_" + ii.getNewName()));
		// 原始文件删除
		new File(srcFile).delete();
		return "redirect:/system/indexImgs";
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
			throws IOException {
		// 原始拓展名
		String oriExtension = FilenameUtils.getExtension(attach
				.getOriginalFilename());

		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName(oriExtension);
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream iis = ImageIO.createImageInputStream(attach
				.getInputStream());
		reader.setInput(iis, true);

		// 对上传文件的尺寸进行校验
		if (reader.getWidth(0) < 1920 || reader.getHeight(0) < 500) {
			// 图片尺寸无误，可以进行剪裁
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("msg", "图片尺寸不符合要求");
			resp.setContentType("text/plain;charset=utf-8");
			resp.getWriter().println(obj.toString());
			return;
		} else {
			// 图片尺寸无误，可以进行剪裁，上传该文件
			// 上下文路径，用户保存资源路径
			String saveUrl = mreq.getContextPath();
			String rootPath = PageParamHolder.getRootPath();
			String savePath = File.separator + "upload" + File.separator + "indexImg" + File.separator;
			// 新文件名
			String newName = new Date().getTime() + "." + oriExtension;

			// 保存记录
			IndexImg ii = new IndexImg();
			ii.setCreDate(new Date());
			ii.setExtention(oriExtension);
			ii.setIndexImgSize(attach.getSize());
			ii.setOriName(attach.getOriginalFilename());
			ii.setNewName(newName);
			ii.setAccessUrl(saveUrl + savePath);
			ii.setWidth(reader.getWidth(0));
			ii.setHeight(reader.getHeight(0));

			indexImgService.addIndexImg(ii);
			// 保存文件
			FileUtils.copyInputStreamToFile(attach.getInputStream(), new File(
					rootPath + savePath + newName));
			resp.getWriter().println(new JSONObject(ii));
		}

	}

	public IindexImgService getIndexImgService() {
		return indexImgService;
	}

	@Inject
	public void setIndexImgService(IindexImgService indexImgService) {
		this.indexImgService = indexImgService;
	}

}
