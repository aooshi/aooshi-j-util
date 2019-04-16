package org.aooshi.j.util.entity;


public class ActionResult {
	
	public final static int CODE_SUCCESS = 0;
	public final static int CODE_FAILURE = 1; 

	private int code;
	private String message;
	private Object data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public void Failure(String message)
	{
		this.code = CODE_FAILURE;
		this.message = message; 
	}
	
	public void Success()
	{
		this.code = CODE_SUCCESS;
		this.message = "";
	}
		
	public boolean isSuccess()
	{
		return this.code == CODE_SUCCESS;
	}
	
	public boolean isFailure()
	{
		return this.code == CODE_FAILURE;
	}
}
