package br.com.unimedceara.core.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileServerConfig {
	
	private String name;
	
	private Boolean publico;
	
	private Date expirationDate;
}
