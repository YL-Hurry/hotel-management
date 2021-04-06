package com.yue.validation;

import org.hibernate.query.Query;

import com.yue.dao.UserDAO;
import com.yue.dao.WaiterDAO;
import com.yue.pojo.User;

public class LoginValidation {
	public boolean checkusername(UserDAO userDao,String username) {
		if(userDao.getUser(username)==null) {
			return true;
		}		
		return false;
		
	}
	public boolean checkWaitername(WaiterDAO wDao,String wname) {
		if(wDao.getWaiter(wname)==null) {
			return true;
		}		
		return false;
		
	}
}
