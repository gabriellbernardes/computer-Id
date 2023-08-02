package br.com.unimedceara.core.mappers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import br.com.unimedceara.core.dtos.FileDto;

/**
 * Classe que concentra funções para mapear diferentes classes que representem arquivos.
 * 
 * @see {@link FileDto FileDto}
 * @see {@link MultipartFile MultipartFile}
 * 
 * @author joao
 *
 */
public class FileMapper {
	
	/**
	 * transforma uma instância de {@link MultipartFile MultipartFile} 
	 * em uma instância de {@link FileDto FileDto}.
	 * 
	 * @param file
	 * @return
	 */
	public static FileDto toFileDto(MultipartFile file) {
		FileDto fileDto = new FileDto();
		try {
			fileDto.setBytes(file.getBytes());
		} catch (IOException e) {
			return null;
		}
		fileDto.setNome(file.getOriginalFilename());
		fileDto.setType(file.getContentType());
		return fileDto;
	}
	
	/**
	 * Transforma uma lista de {@link MultipartFile MultipartFile}
	 * em uma lista de {@link FileDto FileDto}.
	 * @param files
	 * @return
	 */
	public static List<FileDto> toFileDtoList(List<MultipartFile> files) {
		return files.stream().map(f -> FileMapper.toFileDto(f)).collect(Collectors.toList());
	}
	

}
