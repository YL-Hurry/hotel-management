package com.yue.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yue.dao.UserDAO;
import com.yue.dao.WaiterDAO;
import com.yue.pojo.Bill;
import com.yue.pojo.User;
import com.yue.pojo.UserOrder;
import com.yue.validation.DateValidation;

@Controller
public class UserController {
	
	@Autowired
	DateValidation dv;
	@RequestMapping(value = "/bookRoom.htm")
	public String bookRoom() {
		return "book-room";
	}
	@RequestMapping(value = "/viewOrder.htm", method = RequestMethod.GET)
	public ModelAndView viewOrder(HttpServletRequest request, UserDAO userDao) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userid = user.getUserid();
		ModelAndView mv = new ModelAndView("view-order", "orders", userDao.getOrders(userid));
		mv.addObject("rooms", userDao.getRooms(userid));
		
		return mv;
	}
	@RequestMapping(value = "/viewBill.htm")
	public ModelAndView viewBill(HttpServletRequest request, UserDAO userDao) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Bill> bills = userDao.getBills(user.getUserid());
		return new ModelAndView("view-bill", "bills", bills);
	}
	
	@RequestMapping(value = "/orderRoom.htm", method = RequestMethod.POST)
	public ModelAndView orderRoom(UserOrder order,HttpServletRequest request, UserDAO userDao) {
		if(request.getAttribute("unsafe-request") == "true" ){
            return new ModelAndView("error-page", "error", "unsafe input");
        }
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String roomType = request.getParameter("roomType");
		
		String startDate =request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		if(!dv.isBefore(startDate, endDate)) return new ModelAndView("error-page", "error", "end date can not before start Date");
		
		order.setRoomType(roomType);
		order.setStartDate(startDate);
		order.setEndDate(endDate);
		order.setState("false");
		userDao.bookRoom(order,user.getUserid());	
		
		
		session.setAttribute("user", user);			
		return new ModelAndView("book-room", "success", "order success");
	}
	@RequestMapping(value = "/updateOrder.htm", method = RequestMethod.GET)
    public ModelAndView updateTodoGet(@RequestParam("id") Integer userId, UserDAO userDao) {
        ModelAndView mv;
              
        UserOrder order = userDao.getOrder(userId);
        
        if(order != null) {
            mv = new ModelAndView("change-order", "existingorder", order);
        } else mv =  new ModelAndView("error-page","error", "can not change this order");
        
        return mv;
    }
    
	@RequestMapping(value = "/updateOrder.htm", method = RequestMethod.POST)
    public ModelAndView updateTodoPost(@ModelAttribute("existingorder") UserOrder order, UserDAO userDao, HttpServletRequest request) {
		if(request.getAttribute("unsafe-request") == "true" ){
            return new ModelAndView("error-page", "error", "unsafe input");
        }
		
		if(!dv.isBefore(order.getStartDate(), order.getEndDate())) return new ModelAndView("error-page", "error", "end date can not before start Date");
        ModelAndView mv;
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.deleteOrder(order.getId());
		user.addOrder(order);
		session.setAttribute("user", user);
        int result = userDao.updateOrder(order);
      
        if(result == 1) {
            mv = new ModelAndView("redirect:/viewOrder.htm");
   
        } else mv =  new ModelAndView("error-page","error", "can not change this order");
        
        return mv;
    }
	@RequestMapping(value = "/removeOrder.htm", method = RequestMethod.GET)
    public ModelAndView removeTodo(@RequestParam("id") int orderId, UserDAO userDao, HttpServletRequest request) {
        ModelAndView mv;
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.deleteOrder(orderId);
        int result = userDao.deleteOrder(orderId,user.getUserid());
        
        if(result == 1) {
            mv = new ModelAndView("redirect:/viewOrder.htm");
            
        } else mv =  new ModelAndView("error-page", "error", "can not change this order");
        
        return mv;
    }
	
	@RequestMapping(value = "/userCheckout.htm", method = RequestMethod.GET)
	public ModelAndView checkoutRoom(@RequestParam("id") Integer roomid, HttpServletRequest request, UserDAO userDao) {
		int res = userDao.checkout(roomid);
		ModelAndView mv;
		
		if(res == 1) {
            mv = new ModelAndView("redirect:/viewOrder.htm", "success", "check out success");            
        } else mv =  new ModelAndView("error-page", "error", "can not change this order");
		
		return mv;
	}
}
