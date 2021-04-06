package com.yue.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.yue.pojo.Bill;
import com.yue.pojo.Room;
import com.yue.pojo.User;
import com.yue.pojo.UserOrder;

public class UserDAO extends DAO{
	public User checkLogin(String un, String up) {
		String hqlQuery = "FROM User WHERE Username=:un AND UserPassword=:up";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("un", un);
		query.setString("up", up);
		query.setComment("Login in UserDAO");
		return (User) query.uniqueResult();		
	}
	
	public void createUser(User user) {
		begin();
		getSession().save(user);
		close();
	}
	public void bookRoom(UserOrder order, int userid) {
		begin();
		User user = getSession().find(User.class, userid);
		order.setUser(user);
		user.addOrder(order);
	
		getSession().save(order);
		close();
	}
	public List<UserOrder> getOrders(int userid) {
		//get all orders
		String hqlQuery = "FROM UserOrder WHERE user_id=:id";
		Query query = getSession().createQuery(hqlQuery);
		query.setInteger("id", userid);
		
		return query.list();	
	}
	public UserOrder getOrder(int id) {
		UserOrder order = null;
		begin();
		order = getSession().find(UserOrder.class,id);
		close();
		return order;
	}
	public int updateOrder(UserOrder order) {
		int res = 0;
		begin();
		UserOrder neworder = getSession().find(UserOrder.class, order.getId());
		if(order.getState()==null||neworder.getState().equals("true"))return res;
		neworder.setRoomType(order.getRoomType());
		neworder.setStartDate(order.getStartDate());
		neworder.setEndDate(order.getEndDate());
		//getSession().update(neworder);
		commit();
		close();
		res = 1;
		return res;
	}
	public int deleteOrder(int orderid, int userid) {
		//delete one order
		int res = 0;
		begin();
		User user = getSession().find(User.class, userid);
		UserOrder order = getSession().find(UserOrder.class, orderid);
		if(order.getState()==null||order.getState().equals("true"))return res;
		user.deleteOrder(orderid);
		System.out.print("delete"+order.getId());
		getSession().delete(order);
		getSession().flush();
		commit();
		//getSession().clear();
		close();
		res = 1;
		return res;
	}
	public User getUser(String un) {
		String hqlQuery = "FROM User WHERE Username=:un";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("un", un);
		return (User) query.uniqueResult();
	}
	public List<Bill> getBills(int userid) {
		String hqlQuery = "FROM Bill WHERE userid=:id";
		Query query = getSession().createQuery(hqlQuery);
		query.setInteger("id", userid);
		return query.list();
	}
	public int checkout(int roomid) {
		int res = 0;
		System.out.print("set check out val");
		begin();
		Room room = getSession().find(Room.class,roomid); 
		if(room.getCheckout()!=null&&room.getCheckout().equals("true"))return res;
		room.setCheckout("true");
		room.setOutDate(LocalDate.now());
		commit();
		close();
		res = 1;
		return res;
	}
	public List<Room> getRooms(int userid){
		String hqlQuery = "FROM Room WHERE userid=:id";
		Query query = getSession().createQuery(hqlQuery);
		query.setInteger("id", userid);
		return query.list();
	}
}
