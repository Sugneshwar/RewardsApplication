package com.retail.rewards.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
	String errorMessage;
	HttpStatus status;
	
}
