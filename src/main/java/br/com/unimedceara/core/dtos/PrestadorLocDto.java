package br.com.unimedceara.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestadorLocDto {

	@JsonProperty("cod_prestador")
	private String codPrestador;

	private String nome;

	private String tipo;
	
	private String especialidades;
	
	@JsonProperty("nome_unimed")
	private String nomeUnimed;
	
	private String endereco;
	
	private String email;
	
	private Long celular;
	
	private String telefone;
	
	@JsonProperty("contato_outros")
	private String contatoOutros;
	
	@JsonProperty("map_query")
	private String mapQuery;
	
	private Double latitude;
	
	private Double longitude;
}
