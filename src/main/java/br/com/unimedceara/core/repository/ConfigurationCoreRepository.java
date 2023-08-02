package br.com.unimedceara.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unimedceara.core.model.ConfigurationCore;
import br.com.unimedceara.core.services.ConfigurationService;

/**
 * ATENÇÃO! Para recuperar valores de configuração, utilize o serviço {@link ConfigurationService},
 * pois ele possui cachê e evita várias chamadas ao banco.
 * O serviço deve ser injetado com a anotação Autowired.
 * 
 * @author joao
 *
 */
public interface ConfigurationCoreRepository extends JpaRepository<ConfigurationCore, String>{
	
	Optional<ConfigurationCore> findByChave(String chave);
	
	@Query("SELECT c.valor FROM ConfigurationCore c WHERE c.chave = :chave")
	String findValorByChave(@Param("chave") String chave);
		
}
