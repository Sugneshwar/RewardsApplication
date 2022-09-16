package com.retail.rewards.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
	private String errorMessage;
	private HttpStatus status;
}
