package com.wt.service;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBUtils;
import com.wt.dao.UserDao;
import com.wt.dao.UserDaoImpl;
import com.wt.po.*;

/*用户服务类*/
public class UserService {
	/*类实例*/
	private static final UserService instance=new UserService();

//	构造方法
	private UserService() {
		
	}

	public static UserService getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	/*用户增加*/
	public void addUser(User user) throws ServerException{
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();//连接数据库
			UserDao userDao=new UserDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			userDao.save(user);
			DBUtils.commit(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBUtils.rollback(conn);
			
			
		}finally{
			DBUtils.closeConnection(conn);
		}
		
		
	}
	
	/*用户重复检查*/
	public User findUser(User one) throws ServerException {
		// TODO Auto-generated method stub
		User user = null;
		Connection conn=null;
		
			try {
				conn=DBUtils.getConnection();
				UserDao userDao=new UserDaoImpl(conn);
				DBUtils.beginTransaction(conn);
				user=userDao.findUser(one);
				DBUtils.commit(conn);
			} catch (ServerException |SQLException e) {
				// TODO Auto-generated catch block
				DBUtils.rollback(conn);
				
			}
			finally{
				DBUtils.closeConnection(conn);
			}
	
		
		return user;
	}

	public boolean updateUser(User one) throws ServerException {
		// TODO Auto-generated method stub
		Connection conn=null;
		boolean res=false;
		try {
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDaoImpl(conn);
			res=userDao.updateUser(one);
			DBUtils.commit(conn);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(conn);
		}
		return res;
	}

	public User login(String username, String password) throws ServerException {
		// TODO Auto-generated method stub
		User user=null;
		Connection conn=null;
		try {
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			user=userDao.login(username,password);
			DBUtils.commit(conn);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(conn);
		}
		return user;
	}
}
