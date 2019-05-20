package com.knits.jta.common.beans;

import lombok.Data;

@Data
public class ResponseBean<T> {
 
	private String message;	
	private boolean ok;
	private T data;
	
	private int errCode;
	private int totalPage=1;
	
	public static final String DEFAULT_MSG="operation completed with success";
	
	public ResponseBean(String message){
		this(message,true,null,1);
	}
	
	public ResponseBean(T data){
		this(DEFAULT_MSG, data,1);
	}
	
	public ResponseBean( T data, int page){
		this(DEFAULT_MSG, data,page);		
	}
	
	public ResponseBean(){
		this(DEFAULT_MSG, null,1);		
	}
	
	public ResponseBean(String message, T data){
		this(message,true,data,1);	
	}
	
	public ResponseBean(String message, T data, int page){
		this(message,true,data,page);		
	}
	
	public ResponseBean(String message, boolean success,T data){
		this(message, success,null,1);			
	}

	public ResponseBean(String message, boolean success,T data, int page){
		setMessage(message);
		setOk(success);
		setData(data);
		setTotalPage(page);
	}
	

}
