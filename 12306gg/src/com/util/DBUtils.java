package com.util;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*数据库工具类*/
public class DBUtils {
	/*获取数据库连接*/
	public static Connection getConnection() throws ServerException{
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:LLLLU";
			String user="system";
			String password="Lu123456";
			conn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}catch(SQLException e ){
			
		}
		
		return conn;
		
	}
	
	/*开启事务*/
	public static void beginTransaction(Connection conn) throws ServerException{
		try {
			conn.setAutoCommit(false);//不会自动提交事务
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
	/*提交事务*/
	public static void commit(Connection conn) throws ServerException{
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
	/*回滚事务*/
	public static void rollback(Connection conn) throws ServerException{
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
	/*关闭连接*/
	public static void closeConnection(Connection conn) throws ServerException{
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
	}
	/*关闭statement*/
	public static void closestatement(ResultSet rs,Statement sm) throws ServerException{
		try {
			if(rs!=null){
				rs.close();
			}
			if(sm!=null){
				sm.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DBUtils.closestatement(rs, sm);
		}
	}

	
}
