
package top.duyt.web.user.test;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import top.duyt.model.Emur.RoleType;
import top.duyt.web.annotation.AuthorityClz;
import top.duyt.web.annotation.AuthorityMethod;

public class TestClass {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws URISyntaxException, ClassNotFoundException {
		
		
		String pakName = "top.duyt.web.controller";
		
		if (pakName.endsWith("*")) {
			pakName = pakName.substring(0,pakName.length() - 2);
		}
		
		String url = pakName.replace(".", "/");
		
		Map<RoleType, Set<String>> AuthoritySrc = new HashMap<RoleType, Set<String>>();
		
		String p = TestClass.class.getClassLoader().getResource(url).toURI().getPath();
		
		File f = new File(p);
		String fs[] = f.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".class")) return true;
				return false;
			}
		});
		
		for (String file : fs) {
			
			String curClz = pakName + "." + file.substring(0,file.indexOf("."));
			
			Class clz = Class.forName(pakName + "." + file.substring(0,file.indexOf(".")));
			
			//当前类是否是需要权限才能访问的类
			if (clz.isAnnotationPresent(AuthorityClz.class)) {
				
				//获取该类所有方法
				Method[] mds = clz.getDeclaredMethods();
				for (Method method : mds) {
					
					//如果当前方法需要相关权限
					if (method.isAnnotationPresent(AuthorityMethod.class)) {
						//获取权限信息
						AuthorityMethod am = method.getAnnotation(AuthorityMethod.class);
						RoleType[] roles = am.roles();
						
						//添加资源
						for (RoleType role : roles) {
							Set<String> AuthorityMethods =   AuthoritySrc.get(role);
							if (AuthorityMethods == null) {
								AuthorityMethods =  new HashSet<String>();
								AuthoritySrc.put(role, AuthorityMethods);
							}
							AuthorityMethods.add(curClz + "." + method.getName());
						}
					}
				}
			}
		}
		
		
		for (RoleType r : AuthoritySrc.keySet()) {
			for (String src : AuthoritySrc.get(r)) {
				System.out.println(src);
			}
			System.out.println("========================");
		}
	}
}
