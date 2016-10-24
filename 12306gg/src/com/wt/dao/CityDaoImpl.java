package com.wt.dao;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wt.dao.CityDao;
import com.wt.po.City;
import com.wt.po.Province;
import com.util.DBUtils;

public class CityDaoImpl implements CityDao {
	/**
	 * 数据库连接
	 */
	private Connection conn;

	/**
	 * 构造方法
	 * 
	 * @param conn
	 *            数据库连接
	 */
	public CityDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<City> getCityListByProID(String proID) throws SQLException, ServerException {
		// SQL语句
		String find_sql = "SELECT * FROM t_city where father=?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<City> result = new ArrayList<City>();
		try {
			// 设置语句对象，SQL语句条件
			pstmt = conn.prepareStatement(find_sql);
			pstmt.setString(1, proID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 解析结果集对象
				City one = new City();
				one.setCityId(rs.getString("city_ID"));
				one.setCity(rs.getString("city"));
				Province pro = new Province();
				pro.setProvinceId(proID);
				one.setProvince(pro);

				// 保存城市信息列表
				result.add(one);
			}
		} finally {
			DBUtils.closestatement(rs, pstmt);
		}

		return result;
	}
}
