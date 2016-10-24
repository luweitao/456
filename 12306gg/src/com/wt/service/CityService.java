package com.wt.service;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.exception.ServiceException;
import com.wt.dao.CityDao;
import com.wt.dao.CityDaoImpl;
import com.wt.po.City;
import com.util.DBUtils;

/**
 * 城市服务类（采用单例模式实现）
 */
public class CityService {
	/**
	 * 类实例
	 */
	private static final CityService instance = new CityService();

	/**
	 * 取得实例
	 * 
	 * @return 实例对象
	 */
	public static CityService getInstance() {
		return instance;
	}

	/**
	 * 构造方法
	 */
	private CityService() {
	}
	
	/**
	 * 根据省份标识获取所有城市信息列表
	 * @param proID
	 * @return
	 * @throws ServerException 
	 * @throws SQLException
	 */
	public List<City> getCityListByProID(String proID) throws ServerException{
		Connection conn = null;
		List<City> res = null;
		try {
			conn = DBUtils.getConnection();
			CityDao cityDao = new CityDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			res = cityDao.getCityListByProID(proID);
			DBUtils.commit(conn);
		} catch (SQLException | ServerException e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		
		return res;
	}
}
