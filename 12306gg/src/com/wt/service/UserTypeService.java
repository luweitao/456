package com.wt.service;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.exception.ServiceException;
import com.wt.dao.UserTypeDao;
import com.wt.dao.UserTypeDaoImpl;
import com.wt.po.UserType;
import com.util.DBUtils;

/**
 * 旅客类型服务类（采用单例模式实现）
 */
public class UserTypeService {
	/**
	 * 类实例
	 */
	private static final UserTypeService instance = new UserTypeService();

	/**
	 * 取得实例
	 * 
	 * @return 实例对象
	 */
	public static UserTypeService getInstance() {
		return instance;
	}

	/**
	 * 构造方法
	 */
	private UserTypeService() {
	}
	
	/**
	 * 获取所有用户类型列表
	 * @return 用户类型列表
	 * @throws SQLException
	 */
	public List<UserType> getUserTypeList(){
		Connection conn = null;
		List<UserType> res = null;
		try {
			conn = DBUtils.getConnection();
			UserTypeDao userTypeDao = new UserTypeDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			res = userTypeDao.getUserTypeList();
			DBUtils.commit(conn);
		} catch (SQLException | ServerException e) {
			try {
				DBUtils.rollback(conn);
			} catch (ServerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new ServiceException("查询错误", e);
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return res;
	}
}
