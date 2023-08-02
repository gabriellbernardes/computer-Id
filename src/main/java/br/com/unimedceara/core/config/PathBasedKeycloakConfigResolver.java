package br.com.unimedceara.core.config;

import java.io.InputStream;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.OIDCHttpFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe para que, no futuro, este projeto precise acessar
 * mais de um realm. No começo do método "resolve", Será feita uma análise com a request para decidir em qual realm
 * as credenciais serão verificadas. A partir disso, será definido qual arquivo de "*-keycloak.json" no resources será usado.
 *
 */
@Component
public class PathBasedKeycloakConfigResolver implements KeycloakConfigResolver {

	// private static final Map<String, KeycloakDeployment> CACHE = new ConcurrentHashMap<String, KeycloakDeployment>();
	
	@Value("${keycloak.config.clientesunimed.file}")
	private String arquivoConfigClientesUnimed;
	
    @Override
    public KeycloakDeployment resolve(OIDCHttpFacade.Request request) {
        
        InputStream keycloakConfigInputStream = getClass().getResourceAsStream( "/" + arquivoConfigClientesUnimed);
        
        if(keycloakConfigInputStream == null) {
        	throw new RuntimeException("Não foi possível carregar as configurações de segurança");
        }
        
        KeycloakDeployment deployment = KeycloakDeploymentBuilder.build(keycloakConfigInputStream);
        // CACHE.put(key, deployment);
        
        return deployment;
        
    }
}
