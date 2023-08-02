package br.com.unimedceara.core.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.unimedceara.core.dtos.RespostaGenerica;
import br.com.unimedceara.core.model.ConfigurationCore;
import br.com.unimedceara.core.repository.ConfigurationCoreRepository;
import br.com.unimedceara.core.utils.Utils;


@Service
public class ConfigurationService {
	
	@Autowired
	ConfigurationCoreRepository configRepository;
	
	private HashMap<String, String> configs = new HashMap<String, String>();
	
	private String templateEmailAtendenteSolicitacaoGuia; 

	private String templateEmailBeneficiarioSolicitacaoGuia;
	
	/**
	 * Método para sincronizar o cachê local com o banco de dados.
	 * 
	 * @return
	 */
	public RespostaGenerica updateLocalConfig() {
		try {
			updateChaveValores();
			updateTemplateEmailAtendenteSolicitacaoGuia();
			updateTemplateEmailBeneficiarioSolicitacaoGuia();
		} catch (Exception e) {
			return new RespostaGenerica(
					500, 
					"Erro ao recuperar dados de configuração", 
					"Erro ao recuperar dados de configuração", 
					null);
		}
		return RespostaGenerica.ok();
	}
	
	/**
	 * Recupera um valor de configuração a partir da chave.
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if(configs.size() == 0)
			updateLocalConfig();
		
		//TODO: colocar para lancar erro aqui caso a chave nao exista?
		//acho que fica mais consiso.
		//evita ficar colocando codigo pra lançar erro no método que utiliza.
		return configs.get(key);
	}
	
	public String getTemplateEmailAtendenteSolicitacaoGuia() {
		if(!Utils.isValid(this.templateEmailAtendenteSolicitacaoGuia)) {
			updateLocalConfig();
		}
		
		return templateEmailAtendenteSolicitacaoGuia;
	}

	public String getTemplateEmailBeneficiarioSolicitacaoGuia() {
		if(!Utils.isValid(this.templateEmailBeneficiarioSolicitacaoGuia)) {
			updateLocalConfig();
		}
		
		return templateEmailBeneficiarioSolicitacaoGuia;
	}
	
	private void updateChaveValores() throws Exception {
		List<ConfigurationCore> list = configRepository.findAll();
		if(list == null || list.size() < 1)
			throw new Exception();
		configs.clear();
		list.forEach(c -> configs.put(c.getChave(), c.getValor()));
	}
	//TODO: esses metodos podem melhorar. coloar um map que guarda templates em string e fazer 1 metodo só.
	private void updateTemplateEmailAtendenteSolicitacaoGuia() throws Exception {
		String url = this.getValue("solicitacao.email.template.atendente");
		
		ResponseEntity<String> responseEntity = (new RestTemplate()).getForEntity(url, String.class);

		if(responseEntity == null || responseEntity.getStatusCodeValue() != 200 || responseEntity.getBody() == null)
			throw new Exception();

		this.templateEmailAtendenteSolicitacaoGuia = responseEntity.getBody();
	}
	
	private void updateTemplateEmailBeneficiarioSolicitacaoGuia() throws Exception {
		String url = this.getValue("solicitacao.email.template.beneficiario");
		
		ResponseEntity<String> responseEntity = (new RestTemplate()).getForEntity(url, String.class);

		if(responseEntity == null || responseEntity.getStatusCodeValue() != 200 || responseEntity.getBody() == null)
			throw new Exception();

		this.templateEmailBeneficiarioSolicitacaoGuia = responseEntity.getBody();
	}
}
