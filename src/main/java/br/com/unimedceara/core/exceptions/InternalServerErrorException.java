package br.com.unimedceara.core.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * É obrigatoria a passagem de causa para o log.
 * @author joao
 *
 */
public class InternalServerErrorException extends Exception {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Mensagem para quando ocorre erro ao acessar a tabela de configuração no banco de dados.
	 */
	public static String ERRO_TABELA_CONFIG_MSG = "Erro ao tentar recuperar valor na tabela de configuração.";
	
	/**
	 * 
	 * @param logger logger da classe que deu erro. Necessário para que o log faça sentido.
	 * @param causa causa do erro. Vai para o log. DEVE ser declarada
	 * @param msg mensagem de erro que deve ser exibida pro usuario.
	 * @param e erro que DEVE ser passado caso este construtor tenha sido chamado em um catch.
	 */
	public InternalServerErrorException(Logger logger, String causa, String msg, Exception e) {
		super(causa);
		logger.log(Level.SEVERE, causa);
		e.printStackTrace();
	}

	/**
	 * Construtor sem erro 
	 * 
	 * @param logger logger da classe que deu erro. Necessário para que o log faça sentido.
	 * @param causa causa do erro. Vai para o log. DEVE ser declarada
	 * @param msg mensagem de erro que deve ser exibida pro usuario.
	 * @param e erro que DEVE ser passado caso este construtor tenha sido chamado em um catch.
	 */
	public InternalServerErrorException(Logger logger, String causa, String msg) {
		super(causa);
		logger.log(Level.SEVERE, causa);
	}

	/**
	 * Construtor com mensagem de erro padrão.
	 * 
	 * @param logger logger da classe que deu erro. Necessário para que o log faça sentido.
	 * @param causa causa do erro. Vai para o log. DEVE ser declarada
	 * @param msg mensagem de erro que deve ser exibida pro usuario.
	 * @param e erro que DEVE ser passado caso este construtor tenha sido chamado em um catch.
	 */
	public InternalServerErrorException(Logger logger, String causa, Exception e) {
		super(causa);
		logger.log(Level.SEVERE, causa);
		e.printStackTrace();
	}

	/**
	 * Construtor com mensagem de erro padrão.
	 * 
	 * @param lOGGER logger da classe que deu erro. Necessário para que o log faça sentido.
	 * @param causa causa do erro. Vai para o log. DEVE ser declarada
	 */
	public InternalServerErrorException(Logger lOGGER, String causa) {
		super(causa);
		lOGGER.log(Level.SEVERE, causa);
	}

	public static InternalServerErrorException erroTabelaConfig(Logger logger, String... chaves) {
		StringBuilder builder = new StringBuilder();
		builder.append("As seguintes chaves não foram encontradas na tabela de config: ");
		for(String chave : chaves) {
			builder.append(chave + " ");
		}
		return new InternalServerErrorException(logger, builder.toString());
	}
}
