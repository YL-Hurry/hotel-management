package com.yue.pojo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roomtable")
public class Room {
	@Id
	@GeneratedValue (strategy = GenerationType.TABLE)
	private int roomid;
	
	private int userid;
	private String roomType;
	private String checkin;
	private LocalDate inDate;
	private String checkout;
	private LocalDate outDate;
	
	public Room() {}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public LocalDate getInDate() {
		return inDate;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public LocalDate getOutDate() {
		return outDate;
	}

	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}
	
}
