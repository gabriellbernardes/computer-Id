package br.com.unimedceara.core.controllers;

import javax.json.Json;
import javax.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import br.com.unimedceara.core.services.ConfigurationService; 

@RestController
@RequestMapping("admin-tools/")
public class TesteController {
	/** 
	 * Essas duas variáveis são setadas através do ".gitlab-ci.yml" na compilação
	 */
	private String HASH = "__HASH__";
	private String BUILD_DATE = "__BUILD_DATE__";


	@Autowired
	private ConfigurationService configurationService;

	@GetMapping("/teste")
	public String teste() {
		return "API online.";
	}

	@GetMapping("/reload-config")
	public String reloadConfig() {
		configurationService.updateLocalConfig();
		return "configuration reloaded.";
	}
	

	@GetMapping(path = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<String> health() {
    	JsonObject body = Json.createObjectBuilder()
        	.add("hash", HASH)
			.add("build_date", BUILD_DATE)
        	.build();
    	return ResponseEntity.ok(body.toString());
  	}
}
