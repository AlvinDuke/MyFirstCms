package top.duyt.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import top.duyt.dto.PageParamHolder;

public class PageParamFilter implements Filter{

	private int pageSize;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			int pageOffset = 0;
			try {
				pageOffset = Integer
						.parseInt(request.getParameter("pager.offset"));
			} catch (Exception e) {}
			PageParamHolder.setPageSize(pageSize);
			PageParamHolder.setOffSet(pageOffset);
			PageParamHolder.setOrder(request.getParameter("pager.order"));
			PageParamHolder.setSort(request.getParameter("pager.sort"));
			
			chain.doFilter(request, response);
		}
		finally{
			PageParamHolder.removeAll();
		}
		
	}

	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pageSize"));
		} catch (Exception e) {
			pageSize = 10;
		}
	}

}
