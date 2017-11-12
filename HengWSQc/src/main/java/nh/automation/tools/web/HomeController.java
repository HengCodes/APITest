package nh.automation.tools.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import nh.automation.tools.exception.BusinessException;

/**
 * 项目    ：UI自动化测试 SSM
 * 类描述：
 * @author Eric
 * @date 2017年3月13日
 * nh.automation.tools.web
 */
@Controller
public class HomeController {
	@RequestMapping(value={"/","/index","index.jsp"})
	public String index() throws BusinessException{
		return "login";
	}
	
	@RequestMapping("/home")
	public String homePage(){
		return "home/home";
	}
}
