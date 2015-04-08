package top.duyt.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
	private static Properties prop = null;
	
	private PropertiesUtil(String propName) throws IOException{
		if (prop == null) {
			prop = new Properties();
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propName));
		}
	}
	
	/**
	 * 获取实例
	 * @return
	 * @throws IOException 
	 */
	public static PropertiesUtil getInstance(String propName) throws IOException{
		return new PropertiesUtil(propName);
	}
	
	public Object getObj(String propKey){
		return prop.get(propKey);
	}
	
	public String getProps(String propKey){
		return prop.getProperty(propKey);
	}
	
	public void setProps(String key,String value){
		prop.setProperty(key, value);
	}

}
