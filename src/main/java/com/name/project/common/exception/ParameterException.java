package com.name.project.common.exception;
/**   
 * @author ft
 * @version 1.0
 * @date 2015年12月7日 下午1:52:13 
 *
 */
public class ParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterException() {
		super();
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
