package com.tavant.practicalrestapi.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoDataFoundException extends Exception {
	
	public NoDataFoundException(String message) {
		super(message);
		
	}
	
	@Override
	public String toString() {
		return super.toString() ;
	}


}
