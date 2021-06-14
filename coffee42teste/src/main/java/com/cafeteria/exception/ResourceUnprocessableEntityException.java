package com.cafeteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceUnprocessableEntityException extends RuntimeException {


	private static final long serialVersionUID = -1265470046656143792L;

	public ResourceUnprocessableEntityException(String mensagem) {
		super(mensagem);
	}
	
}