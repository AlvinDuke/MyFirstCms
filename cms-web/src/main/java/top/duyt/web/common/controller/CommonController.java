package top.duyt.web.common.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import top.duyt.model.User;
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
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public void login(String username,String password,HttpSession session,HttpServletRequest req,PrintWriter pw) throws NoSuchAlgorithmException {
		User u = userService.loadUserByUserName(username);
		int isValid = 0;
		//用户不存在或者密码不正确
		if (u != null&&
				u.getPassword().equals(SecurityUtil.getEncryptString(password, "MD5", 16))) {
			//用户存在
			isValid = 1;
		}
		session.setAttribute("loginUser", u);
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

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
