package com.wt.po;
/*省份实体类*/
public class Province {
	/*ID*/
	private Integer id;
	
	/*省份标识*/
	private String provinceid;
	
	/*省份名称*/
	private String province;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceid=" + provinceid
				+ ", province=" + province + "]";
	}
	
	
}
