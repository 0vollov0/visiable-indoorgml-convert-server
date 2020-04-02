package com.visiable.convert.exception;

public class FileDownloadException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5130376899766284525L;

	public FileDownloadException(String message) {
		super(message);
	}

	public FileDownloadException(String message, Throwable cause) {
		super(message, cause);
	}
}
