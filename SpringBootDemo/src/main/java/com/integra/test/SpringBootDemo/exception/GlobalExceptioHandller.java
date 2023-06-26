package com.integra.test.SpringBootDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptioHandller {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<AppError> handleException (ResourceNotFoundException ex) {
		
		AppError er=new AppError(601, "Invalid Data");
		
		return new ResponseEntity<AppError>(HttpStatus.BAD_REQUEST);
		
	}

}
