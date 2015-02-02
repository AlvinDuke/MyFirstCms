package top.duyt.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 在请求中添加项目上下文路径
 * @author Alvin Du
 *
 */
public class CommonConstantFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsp = (HttpServletRequest) request;
		//设定上下文路径
		hsp.setAttribute("ctx", hsp.getContextPath());
		//后台管理，保存当前请求的模块名，用于操作界面的选中样式
		hsp.setAttribute("seldModlName", hsp.getRequestURI().split("/")[2]);
		chain.doFilter(hsp, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}

}
