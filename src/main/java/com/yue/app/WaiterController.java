package com.yue.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yue.dao.UserDAO;
import com.yue.dao.WaiterDAO;
import com.yue.pojo.Bill;
import com.yue.pojo.User;
import com.yue.pojo.Waiter;

@Controller
public class WaiterController {
	@RequestMapping(value = "/goManage.htm", method = RequestMethod.GET)
	public String goToManagePage() {				
		
		return "manage-page";
	}
	@RequestMapping(value = "/viewUser.htm", method = RequestMethod.GET)
	public ModelAndView viewUsers(WaiterDAO wDao) {				
		return new ModelAndView("view-user", "users", wDao.getUsers());
		
	}
	@RequestMapping(value = "/working.htm", method = RequestMethod.GET)
	public ModelAndView working(HttpServletRequest request, WaiterDAO wDao) {	
		HttpSession session = request.getSession();
		Waiter waiter = (Waiter) session.getAttribute("waiter");
		wDao.changeState(waiter);
		if(waiter.getState()!=null&&waiter.getState().equals("true")) {
			waiter.setState("false");
		}else {
			waiter.setState("true");
		}
		session.setAttribute("waiter", waiter);
		return new ModelAndView("manage-page","success","working state change");		
	}
	@RequestMapping(value = "/userDetail.htm", method = RequestMethod.GET)
	public ModelAndView userDtail(@RequestParam("id") int userId, WaiterDAO wDao, HttpServletRequest request) {	
		HttpSession session = request.getSession();
		session.setAttribute("userid", userId);
		session.setAttribute("username", wDao.getUsername(userId));
		ModelAndView mv = new ModelAndView("user-detail", "orders", wDao.getUserorders(userId));
		mv.addObject("userRooms", wDao.getRoom(userId));
		
		return mv;
	}
	
	@RequestMapping(value = "/userCheckin.htm", method = RequestMethod.GET)
	public ModelAndView userCheckin(@RequestParam("id") Integer orderid, WaiterDAO wDao, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userid");
		if(wDao.checkorder(orderid)==0)return new ModelAndView("view-user", "success","this order has been checked");
		ModelAndView mv = new ModelAndView("user-detail", "orders", wDao.getUserorders(userId));
		mv.addObject("userRooms", wDao.getRoom(userId));
		mv.addObject("singleRooms",wDao.getRooms("singleRoom"));
		mv.addObject("standardRooms",wDao.getRooms("standardRoom"));
		mv.addObject("bigRooms",wDao.getRooms("bigRoom"));
		
		return mv;
	}
	@RequestMapping(value = "/setRoom.htm", method = RequestMethod.GET)
	public ModelAndView setRoom(@RequestParam("id") Integer roomid, HttpServletRequest request, WaiterDAO wDao) {
		HttpSession session = request.getSession();
		int userid = (Integer) session.getAttribute("userid");
		int res = wDao.checkin(roomid, userid);
		ModelAndView mv = new ModelAndView("view-user","success", "room check in success");
		mv.addObject("users", wDao.getUsers());
		return mv;
	}
	@RequestMapping(value = "/checkoutRoom.htm", method = RequestMethod.GET)
	public ModelAndView checkoutRoom(@RequestParam("id") Integer roomid, HttpServletRequest request, WaiterDAO wDao) {
		HttpSession session = request.getSession();
		int userid = (Integer) session.getAttribute("userid");
		int res = wDao.checkout(roomid);
		ModelAndView mv = new ModelAndView("view-user","success", "room check out success");
		mv.addObject("users", wDao.getUsers());
		return mv;
	}
	@RequestMapping(value = "/preRoom.htm", method = RequestMethod.GET)
	public ModelAndView preRoom(HttpServletRequest request, WaiterDAO wDao) {
		
		
		ModelAndView mv = new ModelAndView("pre-room","finishRooms", wDao.getFinish());
		
		return mv;
	}
	@RequestMapping(value = "/sendBill.htm", method = RequestMethod.GET)
	public ModelAndView sendBill(@RequestParam("id") Integer roomid, HttpServletRequest request, WaiterDAO wDao, Bill bill) {
		
		int res = wDao.preRoom(roomid,bill);
		ModelAndView mv = new ModelAndView("manage-page", "success","bill send success");		
		return mv;
	}
	@RequestMapping(value = "/manageBill.htm", method = RequestMethod.GET)
	public ModelAndView viewBill(HttpServletRequest request, WaiterDAO wDao) {
		
		
		ModelAndView mv = new ModelAndView("manage-bill", "bills",wDao.getBills());		
		return mv;
	}
}
