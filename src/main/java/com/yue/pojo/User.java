package com.yue.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class User {
	@Id
	@Column(name = "UserID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;

	@Basic
	@Column(name = "Username", unique = true)
	private String username;

	@Basic
	@Column(name = "UserPassword")
	private String userpassword;

	@OneToMany(mappedBy = "user")
	private List<UserOrder> orders = new ArrayList<UserOrder>();

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	 public List<UserOrder> getOrders() { return orders; }
	 
	 public void addOrder(UserOrder order) { getOrders().add(order); }
	 
	 public void deleteOrder(UserOrder order) { 
		 Iterator<UserOrder> iterator = getOrders().iterator(); 
		 int id = order.getId();
		 while(iterator.hasNext()){  
		     int i = iterator.next().getId();  
		     if(i == id){  
		         iterator.remove(); 
		     }  
		 }
	 }
	 
	 public void deleteOrder(int id) { 
		 Iterator<UserOrder> iterator = getOrders().iterator(); 
		 while(iterator.hasNext()){  
		     int i = iterator.next().getId();  
		     if(i == id){  
		         iterator.remove(); 
		     }  
		 }
	 }
	 public void setOrders(List<UserOrder> orders) { this.orders = orders; }
	 

}
