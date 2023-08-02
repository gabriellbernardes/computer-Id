package br.com.unimedceara.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
	
	private static String MSG_DEFAULT = "Objeto não encontrado.";
	
	public NotFoundException() {
		super(MSG_DEFAULT);
	};

	public NotFoundException(String string) {
		super(string);
	}

}
