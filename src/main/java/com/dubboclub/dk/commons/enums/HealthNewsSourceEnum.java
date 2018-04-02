package com.dubboclub.dk.commons.enums;

public enum HealthNewsSourceEnum {

	LOCAL_TYPE(0, "本地健康资讯"), THIRD_SOUHU_TYPE(1, "搜狐健康资讯", "http://health.sohu.com/upload/mplist.inc"), THIRD_XYWY_TYPE(
			2, "寻医问药健康资讯", "http://hzyd.hezuo.xywy.com/do.php?sign=ii94QAtaxwoUhuOW");

	public int source;

	public String desc;

	public String requestUrl;

	private HealthNewsSourceEnum(int source, String desc) {
		this.source = source;
		this.desc = desc;
	}

	private HealthNewsSourceEnum(int source, String desc, String requestUrl) {
		this.source = source;
		this.desc = desc;
		this.requestUrl = requestUrl;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

}
