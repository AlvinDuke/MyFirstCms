package top.duyt.web.util;

import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import top.duyt.model.Emur.RoleType;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1255458745599056168L;
	private static WebApplicationContext wac;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring工厂
		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		//初始化权限信息
		try {
			Map<RoleType, Set<String>> src = AuthorityScanner.getSrcByPakName("top.duyt.web.controller");
			this.getServletContext().setAttribute("allAccessableSrc", src);
			System.out.println("all Accessable Src has been loaded "+ Calendar.getInstance().getTime());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取spring工厂
	 * @return
	 */
	public static WebApplicationContext getWac(){
		return wac;
	}
}
