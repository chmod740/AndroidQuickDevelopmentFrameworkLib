package me.hupeng.androidquickdev.lib;

public class AqdSimpleReturnModel {
	private Integer state;
	private String msg;
	
	public AqdSimpleReturnModel() {
		// TODO Auto-generated constructor stub
		state = 0;
		msg = "";
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
