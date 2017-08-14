package com.docstorm.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.docstorm.service.exception.SystemException;

/**
 * @author mohamed.refaat
 *
 */
@RestControllerAdvice(basePackages = {"com.docstorm.rest"})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  {

	
 	@ExceptionHandler(value = {RestResponseException.class })
  	@ResponseBody
    protected ResponseEntity<SystemException> handleRestResponseError(RestResponseException ex) {
         return new ResponseEntity<SystemException>(ex.getSystemException(), new HttpHeaders(), ex.getStatus());
    }
	 	
}
