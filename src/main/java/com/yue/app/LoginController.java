package com.yue.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yue.dao.AdminDAO;
import com.yue.dao.UserDAO;
import com.yue.dao.WaiterDAO;
import com.yue.pojo.Admin;
import com.yue.pojo.User;
import com.yue.pojo.Waiter;
import com.yue.validation.LoginValidation;

@Controller
public class LoginController {
	@Autowired
	LoginValidation lv;
	
	@RequestMapping(value = "/checklogin.htm", method = RequestMethod.POST)
	public ModelAndView handle(HttpServletRequest request, UserDAO userDao, WaiterDAO wDao, AdminDAO adminDao) {
		if(request.getAttribute("unsafe-request") == "true" ){
            return new ModelAndView("login-error", "error", "unsafe input");
        }
		
		String un = request.getParameter("username");
		String up = request.getParameter("password");
		String role = request.getParameter("role");
		HttpSession session = request.getSession();
		
		
		if(role==null||un==null||up==null)return new ModelAndView("login-error", "error", "role, username, password can not be empty");
		
		if(role.equals("user") ) {
			User user = userDao.checkLogin(un, up);
			
			if(user == null) {
				System.out.print("user login error");
			
				return new ModelAndView("login-error", "error", "username or password is incorrect");
			}	
			else {
				session.setAttribute("user", user);
			}
			return new ModelAndView("book-room");
		}else if(role.equals("waiter")){
			Waiter waiter = wDao.checkLogin(un, up);

			if(waiter == null) {
				System.out.print("waiter login error");
				return new ModelAndView("login-error", "error", "username or password is incorrect");
			}	
			else {
				session.setAttribute("waiter", waiter);
				return new ModelAndView("manage-page");
			}
		}else if(role.equals("admin")) {
			Admin admin = adminDao.checkLogin(un, up);

			if(admin == null) {
				System.out.print("admin login error");
				return new ModelAndView("login-error", "error", "username or password is incorrect");
			}	
			else {
				session.setAttribute("admin", admin);
				return new ModelAndView("admin-manage");
			}
		}
		return new ModelAndView("login-error", "error", "other error happens");
	}
	
	@RequestMapping(value = "/signin.htm", method = RequestMethod.POST)
	public String signIn() {
		return "sign-in";
	}
	
	@RequestMapping(value = "/createNew.htm", method = RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request, UserDAO userDao, User user) {
		if(request.getAttribute("unsafe-request") == "true" ){
            return new ModelAndView("login-error", "error", "unsafe input");
        }
		
		String un = request.getParameter("username");
		if(!lv.checkusername(userDao, un))return new ModelAndView("login-error", "error", "username exist");
		String up = request.getParameter("password");
		user.setUsername(un);
		user.setUserpassword(up);
		userDao.createUser(user);
		return new ModelAndView("home");
	}
}
