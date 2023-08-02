package br.com.unimedceara.core.exceptions;

@SuppressWarnings("serial")
public class NoContentException extends Exception {
	private final static String DEFAULT_MSG = "O recurso procurado não existe.";
	
	public NoContentException() {
		super(DEFAULT_MSG);
	}
	
	public NoContentException(String msg) {
		super(msg);
	}
}
