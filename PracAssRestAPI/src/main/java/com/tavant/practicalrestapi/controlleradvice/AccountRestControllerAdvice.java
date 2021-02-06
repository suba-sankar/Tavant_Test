package com.tavant.practicalrestapi.controlleradvice;

import java.util.ArrayList;
import java.util.List;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tavant.practicalrestapi.errorresponse.ErrorResponse;
import com.tavant.practicalrestapi.exception.AccountNotFoundException;

@ControllerAdvice
public class AccountRestControllerAdvice {
	
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(AccountNotFoundException e,WebRequest request)
	{
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT_REQUEST", details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
