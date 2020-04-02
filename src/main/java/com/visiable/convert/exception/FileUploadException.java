package com.visiable.convert.exception;

public class FileUploadException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2481739696032560291L;

	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}
}
