package com.david.common;

/**
 * The rest interface returns the data object
 */
public class ResultVo {
	/**
	 *Results 0 - failed, 1 - successful
	 */
	private int ret;
	/**
	 * Result code
	 */
	private String code;
	/**
	 * Result message
	 */
	private String msg;
	/**
	 * Result data
	 */
	private Object data;
	
	public static final int SUCCESS = 1;
	public static final int FAILURE = 0;
	
	public ResultVo(int ret, String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.ret = ret;
		this.data = data;
	}
	
	public ResultVo() {
		super();
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
