package com.wt.po;

/*城市实体类*/
public class City {
	/*ID*/
	private Integer id;
	
	/*市标识*/
	private String cityid;
	
	/*市名称*/
	private String city;
	
	/*省份*/
	private Province province;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityid=" + cityid + ", city=" + city
				+ ", province=" + province + "]";
	}
	
}
