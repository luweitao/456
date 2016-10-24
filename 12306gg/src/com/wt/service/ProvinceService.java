package com.wt.service;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.exception.ServiceException;
import com.wt.dao.ProvinceDao;
import com.wt.dao.ProvinceDaoImpl;
import com.wt.po.Province;
import com.util.DBUtils;

/**
 * 省份服务类（采用单例模式实现）
 */
public class ProvinceService {
	/**
	 * 类实例
	 */
	private static final ProvinceService instance = new ProvinceService();

	/**
	 * 取得实例
	 * 
	 * @return 实例对象
	 */
	public static ProvinceService getInstance() {
		return instance;
	}

	/**
	 * 构造方法
	 */
	private ProvinceService() {
	}
	
	/**
	 * 获取所有省份列表
	 * @return 省份信息列表
	 * @throws ServerException 
	 * @throws SQLException
	 */
	public List<Province> getProvinceList() throws ServerException{
		Connection conn = null;
		List<Province> res = null;
		try {
			conn = DBUtils.getConnection();
			ProvinceDao provinceDao = new ProvinceDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			res = provinceDao.getProvinceList();
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
