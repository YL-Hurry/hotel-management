package com.yue.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yue.dao.AdminDAO;
import com.yue.dao.UserDAO;
import com.yue.dao.WaiterDAO;
import com.yue.pojo.Admin;
import com.yue.pojo.User;
import com.yue.pojo.Waiter;
import com.yue.validation.LoginValidation;

@Controller
public class AdminController {
	@Autowired
	LoginValidation lv;
	@RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
	@ResponseBody
	public String handle(HttpServletRequest request, AdminDAO adminDao) {
		HttpSession session = request.getSession();
		List<Waiter> waiters = adminDao.getWaiters();
		if(waiters == null)return "no waiter is working now";
		String wname = "";
		for(Waiter w:waiters) {
			wname = wname+" "+w.getWname()+",";
		}
		
		return "Waiter name:"+wname;
	}
	
	@RequestMapping(value = "/createWaiter.htm", method = RequestMethod.POST)
	public ModelAndView createWaiter(HttpServletRequest request, AdminDAO adminDao,WaiterDAO wDao, Waiter waiter) {
		if(request.getAttribute("unsafe-request") == "true" ){
            return new ModelAndView("login-error", "error", "unsafe input");
        }
		
		String wn = request.getParameter("wname");
		if(!lv.checkWaitername(wDao, wn))return new ModelAndView("admin-manage", "success", "waitername exist");
		String wp = request.getParameter("wpassword");
		waiter.setWname(wn);
		waiter.setWpassword(wp);
		waiter.setState("false");
		adminDao.createWaiter(waiter);
		return new ModelAndView("admin-manage","success","Waiter account create successfully");
	}
}
