package com.wt.dao;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import com.wt.po.City;

/**
 * 城市表操作接口
 * @author Administrator
 *
 */
public interface CityDao {
	/**
	 * 根据省份标识获取所有城市信息列表
	 * @param proID
	 * @return
	 * @throws SQLException
	 * @throws ServerException 
	 */
	List<City> getCityListByProID(String proID) throws SQLException, ServerException;
}
