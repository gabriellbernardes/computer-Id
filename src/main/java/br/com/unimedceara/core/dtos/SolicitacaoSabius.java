package br.com.unimedceara.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de autorização de solicitação guia para o sistema sabius.
 * @author joao
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoSabius {
	
	private Long unimedCarteira;
	
	private Long codCarteira;
	
	private String dvCarteira;
	
	private String prestador;
	
	private String contato;
	
	private String email;
	
	private List<String> anexos = new ArrayList<String>();
	
	private String codUsuarioAuditoria;
	
	private String codCaixa;
	
	private Long unimedAtendimento;
	
	private String acomodacao;
}
