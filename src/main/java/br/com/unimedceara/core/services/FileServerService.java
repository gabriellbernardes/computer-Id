package br.com.unimedceara.core.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.unimedceara.core.dtos.FileDto;
import br.com.unimedceara.core.dtos.FileServerConfig;
import br.com.unimedceara.core.exceptions.BadRequestException;
import br.com.unimedceara.core.exceptions.InternalServerErrorException;
import br.com.unimedceara.core.utils.Utils;

/**
 * Serviço responsável por salvar e recuperar arquivos.
 * 
 * @author joao
 *
 */
@Service
public class FileServerService {
	
	private String fileSystemAccessToken;
	
	private Long expirationTime;
	
	@Autowired
	ConfigurationService configurationService;

	/**
	 * Salva uma lista de arquivos no file system, e retorna uma lista de urls.
	 *  
	 * @param files arquivos a serem salvos
	 * @param configs as configurações dos arquivos.
	 * @return
	 * @throws InternalServerErrorException 
	 * @throws BadRequestException 
	 */
	public List<String> saveFiles(List<FileDto> files, List<FileServerConfig> configs) throws InternalServerErrorException, BadRequestException {
		
		if(!Utils.isAllNotNull(files, configs))
			throw new BadRequestException("Parâmetros inexistentes para salvar os arquivos.");
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setBearerAuth(this.getToken());
		
		LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		files.forEach(f -> body.add(f.getNome(), f.getBytes()));
		body.add("configuracao", createJsonConfigString(configs));
		
		String endpoint = configurationService.getValue("fileSystem.upload.endpoint");
		String server = configurationService.getValue("fileSystem.url");
		ResponseEntity<String> responseEntity = (new RestTemplate()).postForEntity(
				server + endpoint, 
				new HttpEntity<LinkedMultiValueMap<String, Object>>(body, headers), 
				String.class);
		
		if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) throw new InternalServerErrorException(Logger.getLogger(FileServerService.class.getName()),"Erro ao salvar os arquivos em: " + server + endpoint);
		JsonArray responseBody = Json.createReader(new StringReader(responseEntity.getBody())).readArray();
		
		List<String> urls = new ArrayList<String>();
		responseBody.forEach(j -> urls.add(server + j.asJsonObject().getString("url")));
		return urls;
	}
	
	/**
	 * Salva uma lista de arquivos no file system
	 * e retorna as urls, na mesma ordem,
	 * com a configuração padrão para todos os arquivos
	 * (acesso publico: false, expirationDate: null)
	 * 
	 * @return
	 * @throws BadRequestException 
	 * @throws InternalServerErrorException 
	 */
	public List<String> saveFiles(List<FileDto> files) throws InternalServerErrorException, BadRequestException {
		List<FileServerConfig> configs = files
				.stream()
				.map(f -> new FileServerConfig(f.getNome(), false, null))
				.collect(Collectors.toList());

		return saveFiles(files, configs);
	}
	
	/**
	 * Salva uma lista de arquivos no file system
	 * e retorna as urls, na mesma ordem. 
	 * Configura, para a lista inteira, o acesso público a url e a data de expiração do arquivo.
	 * 
	 * @param files arquivos
	 * @param acessoPublico define se a url é publica (true) ou se precisa de auth (false)
	 * @param dataExpiracao define uma data de expiração dos arquivos. Caso não tenha, passar null.
	 * @return
	 * @throws BadRequestException 
	 * @throws InternalServerErrorException 
	 */
	public List<String> saveFiles(List<FileDto> files, boolean acessoPublico, Date dataExpiracao) throws InternalServerErrorException, BadRequestException {
		List<FileServerConfig> configs = files
				.stream()
				.map(f -> new FileServerConfig(f.getNome(), acessoPublico, dataExpiracao))
				.collect(Collectors.toList());

		return saveFiles(files, configs);
	}
	
	/**
	 * Pega o token de autorização para acessar o endpoint do fileSystem.
	 * @return
	 * @throws InternalServerErrorException
	 */
	public String getToken() throws InternalServerErrorException {
		if(this.expirationTime != null && (new Date()).getTime() < this.expirationTime)
			return this.fileSystemAccessToken;
		
		String url = configurationService.getValue("fileSystem.auth.url");
		String clientId = configurationService.getValue("fileSystem.auth.clientId");
		String clientSecret = configurationService.getValue("fileSystem.auth.clientSecret");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "client_credentials");
		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);
		ResponseEntity<String> responseEntity = (new RestTemplate()).postForEntity(
				url, 
				new HttpEntity<MultiValueMap<String, String>>(body, headers), 
				String.class);
		
		if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) throw new InternalServerErrorException(Logger.getLogger(FileServerService.class.getName()),"Erro ao buscar o token em: " + url);
		JsonObject responseBody = Json.createReader(new StringReader(responseEntity.getBody())).readObject();
		
		this.fileSystemAccessToken = responseBody.getString("access_token");
		long expiresIn = (long) responseBody.getInt("expires_in");
		//Preciso multiplicar por mil pq a API retorna em segundos e "getTime()" trabalha com miliseconds.
		this.expirationTime = (new Date()).getTime() + (expiresIn * 1000);
		
		return this.fileSystemAccessToken;
	}
	
	/**
	 * Transforma uma lista de configurações em uma String que representa o JSON
	 * que deve ser passado como valor do parâmetro "configuracao"
	 * @param configs
	 * @return
	 */
	private String createJsonConfigString(List<FileServerConfig> configs) {
		JsonObjectBuilder mainJsonBuilder = Json.createObjectBuilder();
		configs.forEach(c -> {
			JsonObjectBuilder innerBuilder = Json.createObjectBuilder()
					.add("name", c.getName())
					.add("public", c.getPublico());
			if(c.getExpirationDate() != null)
				innerBuilder.add("expirationDate", c.getExpirationDate().toString());
			mainJsonBuilder.add(c.getName(), innerBuilder.build());
		});

		return mainJsonBuilder.build().toString();
	}
	
}
