package com.retail.rewards.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.retail.rewards.model.ErrorResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({ CustomerNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> resourceNotFoundException(CustomerNotFoundException ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("Customer Id is not existing. Not valid data.");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handle(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage("Internal Server Error");
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
