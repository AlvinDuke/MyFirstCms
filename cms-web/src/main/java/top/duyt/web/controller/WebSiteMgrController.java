package top.duyt.web.controller;

import java.io.IOException;

import org.jboss.logging.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import top.duyt.utils.PropertiesUtil;
@Controller
@RequestMapping("/webInfo")
public class WebSiteMgrController {

	PropertiesUtil pu = null;
	
	public WebSiteMgrController() {
		try {
			pu = PropertiesUtil.getInstance("websiteInfo.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/websiteInfos")
	public String websiteInfos(Model model) {
		model.addAttribute("about",pu.getProps("about"));
		model.addAttribute("invitedSite",pu.getProps("invitedSite"));
		return "webInfo/websiteInfos";
	}
	
	@RequestMapping(value = "/saveInfos")
	public String saveInfos(@Param String websiteInfo) {
		
		pu.setProps("about", websiteInfo);
		
		return "redirect:/webInfo/websiteInfos";
	}
	
	@RequestMapping(value = "/saveSites")
	public String saveSites(@Param String accessCode) {
		
		pu.setProps("invitedSite", accessCode);
		
		return "redirect:/webInfo/websiteInfos";
	}
	
}
