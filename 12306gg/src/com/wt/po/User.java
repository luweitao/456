package com.wt.po;

import java.util.Date;

//用户实体类
public class User {

//	ID
	private Integer id;
	
//	用户名
	private String username;
	
//	密码
	private String password;
	
//	确认密码
	private String password2;
	
//	权限（1、管理员 2、普通用户）
	private String rule;
	
//	真实姓名
	private String realname;
	
//	性别（0、男 1、女）
	private String sex;
	
//	城市信息
	private City city;
	
//	证件类型
	private CertType certType;
	
//	证件号码
	private String cert;
	
//	生日
	private Date birthday;
	
//	旅客类型
	private UserType userType;
	
//	备注信息
	private String content;
	
//	用户状态
	private String status;
	
//	登录IP
	private String loginip;
	
//	用户头像路径
	private String imagePath;
	
	private String code;
	
	private boolean autoLogin;

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public CertType getCertType() {
		return certType;
	}

	public void setCertType(CertType certType) {
		this.certType = certType;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", password2=" + password2 + ", rule=" + rule
				+ ", realname=" + realname + ", sex=" + sex + ", city=" + city
				+ ", certType=" + certType + ", cert=" + cert + ", birthday="
				+ birthday + ", userType=" + userType + ", content=" + content
				+ ", status=" + status + ", loginip=" + loginip
				+ ", imagePath=" + imagePath + "]";
	}
	
}
