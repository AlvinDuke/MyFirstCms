package top.duyt.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import top.duyt.model.Role;
import top.duyt.model.User;
import top.duyt.model.Emur.RoleType;
import top.duyt.service.IRoleService;
import top.duyt.service.IUserService;
import top.duyt.utils.CaptchaUtil;
import top.duyt.utils.SecurityUtil;

@Controller
@RequestMapping("/common")
public class CommonController {

	private IUserService userService;

	@RequestMapping("/loginInput")
	public String gotoLogin(){
		return "signin";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "signin";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public void login(String username,String password,HttpSession session,HttpServletRequest req,PrintWriter pw) throws NoSuchAlgorithmException {
		User u = userService.loadUserByUserName(username);
		int isValid = 0;
		//用户不存在或者密码不正确
		if (u != null&&
				u.getPassword().equals(SecurityUtil.getEncryptString(u.getUsername(),password, "MD5", 16))) {
			//用户存在
			isValid = 1;
			session.setAttribute("loginUser", u);
			
			//当前用户拥有的可访问资源
			Set<String> userSrc = new HashSet<String>();
			//获取所有需要权限的资源
			Map<RoleType, Set<String>> allSrc = (Map<RoleType, Set<String>>) session.getServletContext().getAttribute("allAccessableSrc");
			
			//保存当前用户是否是管理员的标记
			boolean isAdmin = false;
			List<Role> rs =  userService.ListRolesByUid(u.getId());
			for (Role role : rs) {
				//如果当前用户是管理员
				if (role.getRoleType() == RoleType.ROLE_ADMIN) {
					isAdmin = true;
					session.removeAttribute("usersAcesableSrc");
					break;
				}
				else{
					//不是管理员，则添加当前用户，当前角色所拥有的访问资源
					userSrc.addAll(allSrc.get(role.getRoleType()));
				}
			}
			session.setAttribute("usersAcesableSrc", userSrc);
			session.setAttribute("isAdmin", isAdmin);
		}
		pw.print(isValid); 
		pw.flush(); 
		pw.close(); 
	}
	
	@RequestMapping("/ChkCaptcha")
	public void checkCaptcha(String checkcode,HttpSession session,HttpServletRequest req,PrintWriter pw){
		int isValid = 0;
		if(checkcode != null && checkcode.trim().equals(session.getAttribute("checkcode"))){
			isValid = 1;
		}
		pw.print(isValid); 
		pw.flush(); 
		pw.close(); 
	}
	
	@RequestMapping("/captcha")
	public void generateCaptcha(HttpSession session,HttpServletResponse resp) throws IOException{
		resp.setContentType("image/jpg");
		OutputStream os = resp.getOutputStream();
		String checkCode = CaptchaUtil.generateCaptcha(os, 150, 44, 3, 200, 7);
		session.setAttribute("checkcode", checkCode);
	}
	
	@RequestMapping("/noAuthority")
	public String noAuthority(){
		return "noAuthority";
	}

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
