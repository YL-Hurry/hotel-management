package com.yue.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import com.yue.pojo.Bill;
import com.yue.pojo.Room;
import com.yue.pojo.User;
import com.yue.pojo.UserOrder;
import com.yue.pojo.Waiter;

public class WaiterDAO extends DAO{
	public Waiter checkLogin(String un, String up) {
		String hqlQuery = "FROM Waiter WHERE wname=:un AND wpassword=:up";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("un", un);
		query.setString("up", up);
		query.setComment("Login in WaiterDAO");
		return (Waiter) query.uniqueResult();		
	}
	public Waiter getWaiter(String wname) {
		String hqlQuery = "FROM Waiter WHERE wname=:wn";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("wn", wname);
		return (Waiter) query.uniqueResult();
	}
	
	public List<User> getUsers(){
		String hqlQuery = "FROM User";
		Query query = getSession().createQuery(hqlQuery);	
		System.out.print("get all users");
		return query.list();
	}
	public String getUsername(int userid) {
		begin();
		User user = getSession().find(User.class,userid); 
		close();
		return user.getUsername();
	}
	public List<UserOrder> getUserorders(int userid){
		String hqlQuery = "FROM UserOrder WHERE user_id=:id";
		Query query = getSession().createQuery(hqlQuery);
		query.setInteger("id", userid);
		
		return query.list();
	}
	public List<Room> getRooms(String roomType) {
		String hqlQuery = "FROM Room WHERE userid=:id AND roomType=:rt AND checkin=:ci AND checkout=:co";
		Query query = getSession().createQuery(hqlQuery);
		query.setInteger("id", -1);
		query.setString("rt", roomType);
		query.setString("ci", "false");
		query.setString("co", "false");
		return query.list();
	}
	public List<Room> getRoom(int userid) {
		String hqlQuery = "FROM Room WHERE userid=:id AND checkin=:ci AND checkout=:co";
		Query query = getSession().createQuery(hqlQuery);
		query.setInteger("id", userid);
		query.setString("ci", "true");		
		query.setString("co", "false");
		return query.list();
	}
	public int checkin(int roomid, int userid) {
		int res = 0;
		System.out.print("set check in val");
		begin();
		Room room = getSession().find(Room.class,roomid); 
		room.setCheckin("true");
		room.setInDate(LocalDate.now());
		room.setUserid(userid);
		commit();
		close();
		res = 1;
		return res;
	}
	public int checkout(int roomid) {
		int res = 0;
		System.out.print("set check out val");
		begin();
		Room room = getSession().find(Room.class,roomid); 
		room.setCheckout("true");
		room.setOutDate(LocalDate.now());
		commit();
		close();
		res = 1;
		return res;
	}
	public int preRoom(int roomid, Bill bill) {
		
		int res = 0;
		System.out.print("prepare room val");
		begin();
		Room room = getSession().find(Room.class,roomid); 
		
		long daysDiff = ChronoUnit.DAYS.between(room.getInDate(), room.getOutDate());
		if(daysDiff == 0) daysDiff = 1;
		bill.setUserid(room.getUserid());
		bill.setRoomid(room.getRoomid());
		bill.setInDate(room.getInDate());
		bill.setOutDate(room.getOutDate());
		bill.setDays(daysDiff);
		bill.setPrice(price(room.getRoomType(),daysDiff));
		getSession().save(bill);
		
		room.setCheckin("false");
		room.setCheckout("false");
		room.setUserid(-1);
		commit();
		close();
		res = 1;
		return res;
	}
	public List<Room> getFinish(){
		String hqlQuery = "FROM Room WHERE checkin=:ci AND checkout=:co";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("ci", "true");		
		query.setString("co", "true");
		return query.list();
	}
	
	public double price(String roomType, long days) {
		double res = 0;
		if(roomType!=null&&roomType.equals("singleRoom")) {
			res = 100*days;
		}else if(roomType!=null&&roomType.equals("bigRoom")){
			res = 150*days;
		}else {
			res = 200*days;
		}
		
		return res;
	}
	public List<Bill> getBills(){
		String hqlQuery = "FROM Bill";
		Query query = getSession().createQuery(hqlQuery);	
		System.out.print("get all bills");
		return query.list();
	}
	public int checkorder(int orderid) {
		// TODO Auto-generated method stub
		int res = 0;
		System.out.print("set check out val");
		begin();
		UserOrder order = getSession().find(UserOrder.class,orderid); 
		if(order.getState()!=null&&order.getState().equals("true"))return res;
		order.setState("true");
		commit();
		close();
		res = 1;
		return res;
	}
	public void changeState(Waiter waiter) {
		// TODO Auto-generated method stub
		if(waiter == null) return;
		System.out.print("set check out val");
		begin();
		Waiter newwaiter = getSession().find(Waiter.class,waiter.getId()); 
		if(newwaiter.getState()!=null&&newwaiter.getState().equals("true")) {
			newwaiter.setState("false");
		}else {
			newwaiter.setState("true");
		}
		commit();
		close();
		
	}
}
