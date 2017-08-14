package com.docstorm.rest.exception;

import org.springframework.http.HttpStatus;
import com.docstorm.service.exception.SystemException;

public class RestResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private SystemException systemException;

	public RestResponseException() {
	}

	public RestResponseException(HttpStatus status, SystemException systemException) {
		this.status = status;
		this.systemException = systemException;
	}

	public SystemException getSystemException() {
		return systemException;
	}

	public void setSystemException(SystemException systemException) {
		this.systemException = systemException;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
