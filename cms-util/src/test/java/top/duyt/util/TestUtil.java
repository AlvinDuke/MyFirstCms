package top.duyt.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;

import org.junit.Test;

import freemarker.template.TemplateException;
import top.duyt.testModel.User;
import top.duyt.utils.FreeMarkerUtil;
import top.duyt.utils.JSONUtil;
import top.duyt.utils.Pinyin4jUtil;
import top.duyt.utils.SecurityUtil;
import top.duyt.utils.ThumbnailsUtil;

public class TestUtil {

	public static void main(String[] args) {
		User u = new User(1, "haha", 2);
		User u2 = new User(2, "heihei", 3);
		User u3 = new User(3, "houhou", 4);

		List<User> us = new ArrayList<User>();
		us.add(u);
		us.add(u2);
		us.add(u3);

		Map<Object, Object> umap = new HashMap<Object, Object>();
		umap.put(u.getId(), u);
		umap.put(u2.getId(), u2);

		System.out.println(JSONUtil.bean2JSON(umap));
	}

	@Test
	public void testSecurityUtil() throws NoSuchAlgorithmException {

		String rel = SecurityUtil.getEncryptString("duyt1", "1", "md5", 16);

		System.out.println(rel);
	}

	@Test
	public void testPinyin4jUtil() throws NoSuchAlgorithmException {

		String str = Pinyin4jUtil.converterToFirstSpell("女人");

		System.out.println(str);
	}

	@Test
	public void testThumbnailsUtil1() throws IOException {
		ThumbnailsUtil
				.specifyResize(
						2000,
						3000,
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087.jpg",
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087_big.jpg");
	}

	@Test
	public void testThumbnailsUtil2() throws IOException {
		ThumbnailsUtil
				.scaleResize(
						0.1f,
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087.jpg",
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087_scale0.1.jpg");
	}

	@Test
	public void testThumbnailsUtil3() throws IOException {
		ThumbnailsUtil
				.forceResize(
						2000,
						3000,
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087.jpg",
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087_big_force.jpg");
	}

	@Test
	public void testThumbnailsUtil4() throws IOException {
		ThumbnailsUtil
				.specifyRotate(
						90,
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087.jpg",
						"D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087_rotate.jpg");
	}

	@Test
	public void testThumbnailsUtil5() throws IOException {
		Thumbnails
				.of("D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087.jpg")
				.sourceRegion(Positions.TOP_LEFT, 400, 400)
				.size(400, 400)
				.keepAspectRatio(false)
				.toFile("D:\\Program Files (x86)\\wallPaper\\SogouWP\\Net\\WallPaper\\43087_source.jpg");
	}
	
	/**
	 * freemarker标准输出测试
	 * @throws IOException
	 * @throws TemplateException 
	 */
	@Test
	public void testFreeMarkerSto() throws IOException, TemplateException {
		
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("username", "都较瘦");
		FreeMarkerUtil fmu = FreeMarkerUtil.getInstance("/ftl");
		fmu.stoPrint(datas, "helloFreeMarker.ftl");
		
	}
	
	/**
	 * freemarker文件输出测试
	 * @throws IOException
	 * @throws TemplateException 
	 */
	@Test
	public void testFreeMarkerFileOut() throws IOException, TemplateException {
		
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("username", "都较瘦");
		FreeMarkerUtil fmu = FreeMarkerUtil.getInstance("/ftl");
		fmu.filePrint(datas, "helloFreeMarker.ftl","c:/test.html");
		
	}
}
