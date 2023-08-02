package br.com.unimedceara.core.exceptions;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
	
	public BadRequestException(String msg) {
		super(msg);
	}
}
