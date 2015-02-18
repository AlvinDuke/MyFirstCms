package top.duyt.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.duyt.model.User;

/**
 * 检查是否是游客访问，游客访问用户内容需登录
 * @author Alvin Du
 *
 */

public class AuthorityFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrep = (HttpServletRequest) req;
		HttpServletResponse hresp = (HttpServletResponse) resp;
		User u = (User) hrep.getSession().getAttribute("loginUser");
		//用户未登录
		if(u == null){
			//重定向到登陆页面
			hresp.sendRedirect(hrep.getContextPath() + "/common/loginInput");
			return;
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
