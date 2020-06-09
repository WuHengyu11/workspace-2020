package cn.wolfcode.managesystem.util;

public class LogicException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public LogicException(){
		super();
	}
	
	public LogicException(String message,Throwable cause){
		super(message,cause);
	}
	
	public LogicException(String message){
		super(message);
	}
}
