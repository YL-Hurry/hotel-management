package com.yue.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.yue.pojo.Admin;
import com.yue.pojo.User;
import com.yue.pojo.Waiter;

public class AdminDAO extends DAO{

	public Admin checkLogin(String un, String up) {
		// TODO Auto-generated method stub
		String hqlQuery = "FROM Admin WHERE adminname=:un AND adminpassword=:up";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("un", un);
		query.setString("up", up);
		query.setComment("Login in AdminDAO");
		return (Admin) query.uniqueResult();		
	}

	public List<Waiter> getWaiters() {
		// TODO Auto-generated method stub
		String hqlQuery = "FROM Waiter WHERE state=:st";
		Query query = getSession().createQuery(hqlQuery);
		query.setString("st", "true");

		query.setComment("Login in AdminDAO");
		return query.list();
	}

	public void createWaiter(Waiter waiter) {
		// TODO Auto-generated method stub		
			begin();
			getSession().save(waiter);
			close();		
	}

}
