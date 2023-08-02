package br.com.unimedceara.core.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.unimedceara.core.exceptions.InternalServerErrorException;
import br.com.unimedceara.core.services.FileServerService;

public class Utils {

	private Utils(){}
	
	public static final String CHAVE_CRITOGRAFICA_BOLETO = "2dad3b15a3abcb29f82e1d5547e8cd1a";
	public static final String CHAVE_CRIPTOGRAFICA_IMAGEM_GUIA = "029c02fc131cc2ce29905f8e7f4c3039";
	
	public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encode(crypted));
    }
	
	public static String decrypt(String input, String key) {                
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(Base64.getDecoder().decode(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(output);
    }
	
	/**
	 * Método que encapsula a criação do header para um ResponseEntity de pdf.
	 * @param nomeArquivo nome que será dado ao pdf.
	 * @return
	 */
	public static HttpHeaders pdfHeaders(String nomeArquivo) {
		HttpHeaders headersPDF = new HttpHeaders();
		headersPDF.setContentDispositionFormData(nomeArquivo, nomeArquivo);
		headersPDF.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headersPDF.setContentType(MediaType.APPLICATION_PDF);
		
		return headersPDF;
	}
	
	/**
	 * Verifica se todos os objetos passados NÃO são nulos.
	 * Se pelo menos um objeto for nulo, retorna falso.
	 * 
	 * @param objs
	 * @return
	 */
	public static boolean isAllNotNull (Object... objs) {
		for(Object obj : objs) {
			if(obj == null) return false;
		}
		return true;
	}
	
	/**
	 * Verifica se todos os objetos passados são nulos.
	 * Se pelo menos um objeto NÃO for nulo, retorna falso.
	 * 
	 * @param objs
	 * @return
	 */
	public static boolean isAllNull (Object... objs) {
		for(Object obj : objs) {
			if(obj != null) return false;
		}
		return true;
	}
	
	/**
	 * Valida se objetos que dependem uns dos outros são válidos.
	 * Ou seja, ou todo mundo é nulo ou ninguém é.
	 * Usar em situações em que se pelo menos um objeto de um conjunto de objetos for nulo surgirá um erro,
	 * mas se todos forem nulos então não há problema.
	 * Em lógica, é um OU EXCLUSIVO NEGADO, !XOR.
	 * 
	 * @param objs
	 * @return
	 */
	public static boolean validaInterdependenciaSatisfeita(Object... objs) {
		return Utils.isAllNull(objs) || Utils.isAllNotNull(objs);
	}
	
	public static List<UnzipedFile> unzip(byte[] zipped) throws IOException {
		final int BUFFER_SIZE = 2048;

		InputStream zippedInputStream = new ByteArrayInputStream(zipped);
		ZipInputStream zipInputStream = new ZipInputStream(zippedInputStream);
		ZipEntry entry;
		List<UnzipedFile> result = new ArrayList<UnzipedFile>();

		while ((entry = zipInputStream.getNextEntry()) != null) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			UnzipedFile file = new UnzipedFile();

			int count;
			byte data[] = new byte[BUFFER_SIZE];

			if (!entry.isDirectory() && !entry.getName().startsWith("__MACOSX")) {
				BufferedOutputStream out = new BufferedOutputStream(byteArrayOutputStream, BUFFER_SIZE);
				while ((count = zipInputStream.read(data, 0, BUFFER_SIZE)) != -1) {
					out.write(data, 0, count);
				}

				out.flush();
				out.close();

				file.setFileType(entry.getName().split("\\.")[1]);
				file.setByteArrayOutputStream(byteArrayOutputStream);

				result.add(file);
			}
		}

		zipInputStream.close();

		return result;
	}

	/**
	 * Retorna uma data a partir da string e do padrão de formatação.
	 * Retorna Null se houver erro de transformação.
	 * 
	 * @return
	 */
	public static Date stringToDate(String data, String padrao) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(padrao);
			Date dataFormatada = null;
            dataFormatada = sdf.parse(data);
			return dataFormatada;
        } catch (Exception e) {
            return null;
        }
	}
	
	/**
	 * Retorna uma data a partir da string com o padrão yyyyMMddHHmmss
	 * Retorna Null se houver erro de transformação.
	 * 
	 * @return
	 */
	public static Date stringToDate(String data) {
		return stringToDate(data, "yyyyMMddHHmmss");
	}

	public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos;

        try {
            bos = Utils.getCompressedImageBytes(image, type);
            byte[] imageBytes = bos.toByteArray();

            Base64.Encoder encoder = Base64.getEncoder();
            imageString = encoder.encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
	public static ByteArrayOutputStream getCompressedImageBytes(BufferedImage imageByte, String type)
			throws IOException {
		ByteArrayOutputStream compressed = new ByteArrayOutputStream();

		ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(type.toUpperCase()).next();
		ImageOutputStream outputStream = ImageIO.createImageOutputStream(compressed);

		ImageWriteParam imageWriterParam = imageWriter.getDefaultWriteParam();
		imageWriterParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriterParam.setCompressionQuality(0.5f);

		imageWriter.setOutput(outputStream);
		imageWriter.write(null, new IIOImage(imageByte, null, null), imageWriterParam);

		outputStream.close();
		imageWriter.dispose();

		return compressed;
	}
	
	/**
	 * Verifica se a string passada não é nula ou vazia ou preenchida somente com espaços.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValid(String str) {
		return (str != null && !str.isBlank());
	}
	
	/**
	 * Gera uma representação em String do md5 a partir de uma array de bytes.
	 * 
	 * Caso ocorra erros retorna nulo.
	 * 
	 * @param bts
	 * @return
	 */
	public static String md5(byte[] bts) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(bts);
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Transforma um {@link java.util.Date Date} em uma string com formatação BR: dd/MM/yyyy. 
	 * @param data
	 * @return
	 */
	public static String dateToStringBR(Date data) {
		if (data == null) return null;
		return data.toInstant().atZone(ZoneId.systemDefault())
			.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * Retorna verdadeiro se uma lista não é nula nem vazia
	 * 
	 * @param l
	 * @return
	 */
	public static boolean isListValid(List<?> l) {
		return l != null && !l.isEmpty();
	}

	/**
	 * Normaliza uma string para um padrão ASCII
	 * @param str
	 * @return
	 */
	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Transforma uma string que representa um numero de telefone em um long.
	 * 
	 * @param telefone
	 * @return
	 */
	public static Long telefoneStringToLong(String telefone) {
		return Long.parseLong(
			telefone
				.replace(" ", "")
				.replace("+", "")
				.replace("(", "")
				.replace(")", "")
				.replace("-", ""));
	}

	/**
	 * Retorna uma string url encoded. 
	 * @param value
	 * @return
	 */
	public static String urlEncodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	

	public static String getBearerTokenClientCredentials(String host,String realm,String clientId,String clientSecret) throws InternalServerErrorException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "client_credentials");
		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);

		ResponseEntity<String> responseEntity = (new RestTemplate()).postForEntity(
				String.format("%s/auth/realms/%s/protocol/openid-connect/token", host,realm), 
				new HttpEntity<MultiValueMap<String, String>>(body, headers), 
				String.class);
		
		if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) 
			throw new InternalServerErrorException(Logger.getLogger(FileServerService.class.getName()),"Erro ao buscar o token em: " + host);
			
		JsonObject responseBody = Json.createReader(new StringReader(responseEntity.getBody())).readObject();
		
		String fileSystemAccessToken = responseBody.getString("access_token");
		
		return fileSystemAccessToken;
	}


}
