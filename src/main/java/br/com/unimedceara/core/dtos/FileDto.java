package br.com.unimedceara.core.dtos;

import br.com.unimedceara.core.mappers.FileMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um arquivo genérico para este projeto.
 * 
 * @author joao
 * 
 * @see {@link FileMapper FileMapper}
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

	private byte[] bytes;
	
	private String nome;
	
	private String type;
}
