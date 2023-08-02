package br.com.unimedceara.core.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class RespostaGenerica {

	public RespostaGenerica(Integer status, String error, String message, String path) {
		super();
		this.timestamp = new Date();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	public static RespostaGenerica ok() {
		return new RespostaGenerica(200, null, "Operação realizada com sucesso", "");
	}

	private Date timestamp;
	
	private Integer status;
	
	private String error;
	
	private String message;
	
	private String path;
}
