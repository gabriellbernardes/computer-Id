package br.com.unimedceara.core.exceptions;

@SuppressWarnings("serial")
public class ApiIntegrationException extends RuntimeException {
	
	public ApiIntegrationException(String msg) {
		super(msg);
	}
}
