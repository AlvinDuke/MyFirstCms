package top.duyt.web.util;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import top.duyt.model.Emur.RoleType;
import top.duyt.web.annotation.AuthorityClz;
import top.duyt.web.annotation.AuthorityMethod;

public class AuthorityScanner {

	/**
	 * 根据包路径获取需授权的方法，目前设定为只获取一个包下的所有类，不递归获取
	 * 
	 * @param pakName
	 *            包路径，形如:top.duyt.web.controller
	 * @throws URISyntaxException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<RoleType, Set<String>> getSrcByPakName(String pakName)
			throws URISyntaxException, ClassNotFoundException {

		String url = pakName.replace(".", "/");
		Map<RoleType, Set<String>> AuthoritySrc = new HashMap<RoleType, Set<String>>();
		String p = AuthorityScanner.class.getClassLoader().getResource(url)
				.toURI().getPath();

		File f = new File(p);
		String fs[] = f.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".class"))
					return true;
				return false;
			}
		});

		for (String file : fs) {
			//当前类路径
			String curClz = pakName + "."
					+ file.substring(0, file.indexOf("."));
			//获取类的实例
			Class clz = Class.forName(pakName + "."
					+ file.substring(0, file.indexOf(".")));
			// 当前类是否是需要权限才能访问的类
			if (clz.isAnnotationPresent(AuthorityClz.class)) {
				// 获取该类所有方法
				Method[] mds = clz.getDeclaredMethods();
				for (Method method : mds) {
					// 如果当前方法需要相关权限
					if (method.isAnnotationPresent(AuthorityMethod.class)) {
						// 获取权限信息
						AuthorityMethod am = method
								.getAnnotation(AuthorityMethod.class);
						RoleType[] roles = am.roles();
						// 添加资源
						for (RoleType role : roles) {
							Set<String> AuthorityMethods = AuthoritySrc
									.get(role);
							if (AuthorityMethods == null) {
								AuthorityMethods = new HashSet<String>();
							}
							AuthorityMethods.add(curClz + "."
									+ method.getName());
							AuthoritySrc.put(role, AuthorityMethods);
						}
					}
				}
			}
		}

		return AuthoritySrc;

	}
}
