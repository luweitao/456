package com.wt.dao;

import java.rmi.ServerException;
import java.sql.SQLException;

import com.wt.po.User;

/*用户表操作接口*/
public interface UserDao {

	int save(User user)throws SQLException;
	
	
	User findUser(User user)throws SQLException;


	boolean updateUser(User one);


	User login(String username, String password);
}
