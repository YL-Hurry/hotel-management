package com.yue.pojo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userid;
	private int roomid;
	private LocalDate inDate;
	private LocalDate outDate;
	private long days;
	double price;
	
	public Bill() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getInDate() {
		return inDate;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public LocalDate getOutDate() {
		return outDate;
	}

	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}
	
}
