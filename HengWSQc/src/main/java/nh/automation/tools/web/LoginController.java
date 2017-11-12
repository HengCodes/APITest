package nh.automation.tools.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nh.automation.tools.entity.User;
import nh.automation.tools.serviceImpl.UserServiceImp;

@Controller
public class LoginController {

	@Autowired
	private UserServiceImp userService;

	@RequestMapping("/login")
	public String login() {
		return "login/login";
	}

	@RequestMapping("/loginOn")
	public String loginOn(HttpSession session, String userName, String passWord) {
		User user = userService.getUser(userName);
		String userAt = (String) session.getAttribute("user");
		String pwAt = (String) session.getAttribute("pw");
		if (user != null) {
			if (userAt != null) {
				if (userName.equals(userAt)) {
					if (passWord.equals(pwAt)) {
						return "redirect:/home";
					}
				}
			} else if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)) {
				session.setAttribute("user", userName);
				session.setAttribute("pw", passWord);
				return "redirect:/home";

			}
		}
		return "redirect:/login";

	}

	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		ModelAndView andView = new ModelAndView();
		andView.setViewName("login/login");
		return "redirect:login";
	}

}
