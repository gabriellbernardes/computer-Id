package br.com.unimedceara.core.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import br.com.unimedceara.core.dtos.RespostaGenerica;
import br.com.unimedceara.core.exceptions.ApiIntegrationException;
import br.com.unimedceara.core.exceptions.BadRequestException;
import br.com.unimedceara.core.exceptions.ForbiddenException;
import br.com.unimedceara.core.exceptions.InternalServerErrorException;
import br.com.unimedceara.core.exceptions.NoContentException;
import br.com.unimedceara.core.exceptions.NotFoundException;

@RestControllerAdvice
public class ExceptionHandler {
	//TODO: Arranjar um jeito decente de passar o path.

	@org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
	public ResponseEntity<RespostaGenerica> handleBadRequest(BadRequestException ex) {
		RespostaGenerica body = new RespostaGenerica(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<RespostaGenerica> handleInternalServerError(InternalServerErrorException ex) {
		ex.printStackTrace();
		RespostaGenerica body = new RespostaGenerica(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro mapeado", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoContentException.class)
	public ResponseEntity<?> handleNoContent(NoContentException ex) {
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	public ResponseEntity<RespostaGenerica> handleNotFound(NotFoundException ex) {
		RespostaGenerica body = new RespostaGenerica(HttpStatus.NOT_FOUND.value(), "Not found", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<RespostaGenerica> handleForbidden(ForbiddenException ex) {
		RespostaGenerica body = new RespostaGenerica(HttpStatus.FORBIDDEN.value(), "Forbidden", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.FORBIDDEN);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<RespostaGenerica> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		RespostaGenerica body = new RespostaGenerica(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ApiIntegrationException.class)
	public ResponseEntity<RespostaGenerica> handleApiIntegrationException(ApiIntegrationException ex) {
		RespostaGenerica body = new RespostaGenerica(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro mapeado", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.Unauthorized.class)
	public ResponseEntity<RespostaGenerica> handleHttpClientErrorExceptionUnauthorized(HttpClientErrorException.Unauthorized ex) {
		RespostaGenerica body = new RespostaGenerica(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", ex.getMessage(), "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.UNAUTHORIZED);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<RespostaGenerica> handleDefault(Exception ex) {
		ex.printStackTrace();
		RespostaGenerica body = new RespostaGenerica(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro não mapeado", "", "");
		return new ResponseEntity<RespostaGenerica>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
