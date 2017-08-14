package com.docstorm.common;

import org.springframework.http.HttpStatus;

import com.docstorm.rest.exception.RestResponseException;
import com.docstorm.service.exception.RuntimeSystemException;
import com.docstorm.service.exception.SystemException;
import com.docstorm.service.exception.SystemException.ErrorCode;

/**
 * @author mohamed.refaat
 *
 */
public class CommonUtils {
	
	public static RestResponseException handleException(Exception e) {
		if (e instanceof RuntimeSystemException) {
			RuntimeSystemException runtimeException = (RuntimeSystemException) e;
			SystemException systemException = (SystemException) runtimeException.getError();
			if (systemException.getCode() == ErrorCode.NOT_UNIQUE) {
				return new RestResponseException(HttpStatus.CONFLICT,
						new SystemException(systemException.getMessage(), systemException.getCode()));
			} else if (systemException.getCode() == ErrorCode.NULL_PROPERTY) {
				return new RestResponseException(HttpStatus.UNPROCESSABLE_ENTITY,
						new SystemException(systemException.getMessage(), systemException.getCode()));
			} else if (systemException.getCode() == ErrorCode.NOT_FOUND) {
				return new RestResponseException(HttpStatus.NOT_FOUND,
						new SystemException(systemException.getMessage(), systemException.getCode()));
			}
		} else {
			return new RestResponseException(HttpStatus.INTERNAL_SERVER_ERROR,
					new SystemException("Some thing went wrong", ErrorCode.UNEXPECTED_ERROR));
		}
		return null;
	}
	
}
