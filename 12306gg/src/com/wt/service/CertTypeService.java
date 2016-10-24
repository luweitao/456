package com.wt.service;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.exception.ServiceException;
import com.wt.dao.CertTypeDao;
import com.wt.dao.CertTypeDaoImpl;
import com.wt.po.CertType;
import com.util.DBUtils;

/**
 * 证件类型服务类（采用单例模式实现）
 */
public class CertTypeService {
	/**
	 * 类实例
	 */
	private static final CertTypeService instance = new CertTypeService();

	/**
	 * 取得实例
	 * 
	 * @return 实例对象
	 */
	public static CertTypeService getInstance() {
		return instance;
	}

	/**
	 * 构造方法
	 */
	private CertTypeService() {
	}
	
	/**
	 * 获取所有证件类型列表
	 * @return 证件类型列表
	 * @throws ServerException 
	 * @throws SQLException
	 */
	public List<CertType> getCertTypeList() throws ServerException{
		Connection conn = null;
		List<CertType> res = null;
		try {
			conn = DBUtils.getConnection();
			CertTypeDao certTypeDao = new CertTypeDaoImpl(conn);
			DBUtils.beginTransaction(conn);
			res = certTypeDao.getCertTypeList();
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
