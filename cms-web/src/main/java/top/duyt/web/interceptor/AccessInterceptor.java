package top.duyt.web.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import top.duyt.model.User;

public class AccessInterceptor extends HandlerInterceptorAdapter{

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,  Object handler) throws Exception {
		HandlerMethod hm = (HandlerMethod) handler;
		
		User u = (User) request.getSession().getAttribute("loginUser");
		//用户未登录
		if(u == null){
			//重定向到登陆页面
			response.sendRedirect(request.getContextPath() + "/common/loginInput");
			return false;
		}else{
			//登陆用户检查是否是管理员
			boolean isAdmin =  (boolean) request.getSession().getAttribute("isAdmin");
			//管理员用户不进行权限拦截
			if(!isAdmin){
				String curMethod = hm.getBean().getClass().getName()+"."+hm.getMethod().getName();
				Set<String> userSrc = (Set<String>) request.getSession().getAttribute("usersAcesableSrc");
				//用户的权限资源中不包含当前方法，则跳转至未授权画面
				if (!userSrc.contains(curMethod)) {
					response.sendRedirect(request.getContextPath()+"/common/noAuthority");
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
