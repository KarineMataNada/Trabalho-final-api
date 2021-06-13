package com.cafeteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotAcceptableException extends RuntimeException {

	private static final long serialVersionUID = -9034776379799842737L;

	public ResourceNotAcceptableException(String mensagem) {
		super(mensagem);
	}
}