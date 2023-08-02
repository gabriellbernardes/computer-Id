package br.com.unimedceara.core.exceptions;

@SuppressWarnings("serial")
public class ForbiddenException extends Exception{
	private final static String DEFAULT_MSG = "Conforme normas de confidencialidade, não é permitido o acesso a este recurso.";
	
	public ForbiddenException() {
		super(DEFAULT_MSG);
	}
	
	public ForbiddenException(String msg) {
		super(msg);
	}
}
