package com.wt.dao;

import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBUtils;
import com.wt.po.*;

public  class UserDaoImpl implements UserDao {

//	数据库连接
	private Connection conn;
	
	

	public UserDaoImpl(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}

	@Override
	public int save(User user) throws SQLException{
		// TODO Auto-generated method stub
		String save_sql="INSERT INTO t_user VALUES"
				+ "(T_USER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		
		int row=0;
		int idx=1;
		
		try {
			pstmt=conn.prepareStatement(save_sql);
			
			pstmt.setString(idx, user.getUsername());
			pstmt.setString(++idx, user.getPassword());
			pstmt.setString(++idx, user.getRule());
			pstmt.setString(++idx, user.getRealname());
			System.out.println(user.getSex());
			pstmt.setString(++idx, user.getSex());
			
			pstmt.setInt(++idx, user.getCity().getId());
			pstmt.setInt(++idx, user.getCertType().getId());
			pstmt.setString(++idx, user.getCert());
			pstmt.setDate(++idx,new java.sql.Date(user.getBirthday().getTime()));
			pstmt.setInt(++idx, user.getUserType().getId());
			pstmt.setString(++idx, user.getContent());
			pstmt.setString(++idx, user.getStatus());
			pstmt.setString(++idx, user.getLoginip());
			pstmt.setString(++idx, user.getImagePath());
			row=pstmt.executeUpdate();
			
		}finally{
			try {
				DBUtils.closestatement(null, pstmt);
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return row;
	}

	@Override
	public User findUser(User one) throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer find_sql=new StringBuffer(
				"SELECT * FROM t_user WHERE 1=1");
//		查询条件标记
		boolean tag=false;
//		查询条件id字段
		Integer id=one.getId();
		if(id!=null&&id!=0){
			find_sql.append("AND id="+id); //给sql语句末尾加上字符串
			tag=true;
		}
//		查询条件username字段
		String username=one.getUsername();
		if(username!=null&&!username.isEmpty()){
			find_sql.append("AND username='"+username+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件password字段
		String password=one.getPassword();
		if(password!=null&&!password.isEmpty()){
			find_sql.append("AND password='"+password+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
		
//		查询条件rule字段
		String rule=one.getRule();
		if(rule!=null&&!rule.isEmpty()){
			find_sql.append("AND rule='"+rule+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件realname字段
		String realname=one.getRealname();
		if(realname!=null&&!realname.isEmpty()){
			find_sql.append("AND realname LIKE '%"+realname+"%'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件sex字段
		String sex=one.getSex();
		if(sex!=null&&!sex.isEmpty()){
			find_sql.append("AND sex='"+sex+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件city字段
		if(one.getCity()!=null){
			int city=one.getCity().getId();
			if(city!=0){
				find_sql.append("AND city="+city);/*sql语句末尾加上字符串*/
				tag=true;
			}
		}
		
//		查询条件cert_type字段
		if(one.getCertType()!=null){
			int certtype=one.getCertType().getId();
			if(certtype!=0){
				find_sql.append("AND certtype="+certtype);/*sql语句末尾加上字符串*/
				tag=true;
			}
		}
//		查询条件cert字段
		String cert=one.getCert();
		if(cert!=null&&!cert.isEmpty()){
			find_sql.append("AND cert LIKE '%"+cert+"%'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件user_type字段
		if(one.getUserType()!=null){
			int usertype=one.getUserType().getId();
			if(usertype!=0){
				find_sql.append("AND user_type="+usertype);/*sql语句末尾加上字符串*/
				tag=true;
			}
		}
//		查询条件content字段
		String content=one.getContent();
		if(content!=null&&!content.isEmpty()){
			find_sql.append("AND content LIKE '%"+content+"%'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件status字段
		String status=one.getStatus();
		if(status!=null&&!status.isEmpty()){
			find_sql.append("AND status='"+status+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件login_ip字段
		String ip=one.getLoginip();
		if(ip!=null&&!ip.isEmpty()){
			find_sql.append("AND ip='"+ip+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
//		查询条件imager_path字段
		String image=one.getImagePath();
		if(image!=null&&!image.isEmpty()){
			find_sql.append("AND image_path='"+image+"'");/*sql语句末尾加上字符串*/
			tag=true;
		}
		
		if(!tag){
			return null;
		}
		User user=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			pstmt=conn.prepareStatement(find_sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()){
			//		解析结果，封装对象	
				user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRule(rs.getString("rule"));
				user.setRealname(rs.getString("realname"));
				user.setSex(rs.getString("sex"));
				
//			City
				City city=new City();
				city.setId(rs.getInt("city"));
				user.setCity(city);
				
//			CertType
				CertType certType=new CertType();
				
				certType.setId(rs.getInt("cert_type"));
				user.setCertType(certType);
				
//			UserType
				UserType userType=new UserType();
				userType.setId(rs.getInt("user_type"));
				user.setUserType(userType);
				
				user.setContent(rs.getString("content"));
				user.setStatus(rs.getString("status"));
				user.setLoginip(rs.getString("login_ip"));
				user.setImagePath(rs.getString("image_path"));
			}
			
		} finally{
			try {
				DBUtils.closestatement(rs, pstmt);
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean updateUser(User one) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
