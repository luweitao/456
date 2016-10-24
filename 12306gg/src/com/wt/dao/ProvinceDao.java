package com.wt.dao;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import com.wt.po.Province;

/**
 * 省份操作接口
 * @author Administrator
 *
 */
public interface ProvinceDao {
	/**
	 * 获取所有省份列表
	 * @return 省份信息列表
	 * @throws SQLException
	 * @throws ServerException 
	 */
	List<Province> getProvinceList() throws SQLException, ServerException;
}
