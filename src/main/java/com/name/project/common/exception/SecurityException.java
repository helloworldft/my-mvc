package com.name.project.common.exception;
/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月7日 下午1:51:50 
 *
 */
public class SecurityException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	
	public SecurityException() {
		super();
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(String code, String message) {
		super(message);
		this.code = code;
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
