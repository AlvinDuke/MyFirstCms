package top.duyt.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class FreeMarkerUtil {
	
	private static FreeMarkerUtil util;
	private static Configuration cfg;
	
	private FreeMarkerUtil(){
		
	}
	
	/**
	 * 获取工具实例
	 * @param packagePrefix 模板文件所在包的路径前缀
	 * @return
	 */
	public static FreeMarkerUtil getInstance(String packagePrefix){
		
		if (util == null) {
			cfg = new Configuration(new Version("2.3.0"));
			cfg.setClassForTemplateLoading(FreeMarkerUtil.class, packagePrefix);
			cfg.setEncoding(Locale.CHINESE, "utf-8");
			util = new FreeMarkerUtil();
		}
		return util;
	}
	
	/**
	 * 标准输出，输出模板内容
	 * @param datas 数据源
	 * @param tempName 模板名
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void stoPrint(HashMap<String, Object> datas, String tempName)
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		Template temp = cfg.getTemplate(tempName);
		temp.process(datas, new PrintWriter(System.out));
	}
	
	/**
	 * 文件输出，填充动态数据到模板，输出到文件
	 * @param datas 数据源
	 * @param tempName 模板名
	 * @param filePath
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void filePrint(HashMap<String, Object> datas, String tempName,String filePath) throws TemplateException, IOException{
		Template temp = cfg.getTemplate(tempName);
		temp.process(datas, new FileWriter(new File(filePath)));
	}

}
