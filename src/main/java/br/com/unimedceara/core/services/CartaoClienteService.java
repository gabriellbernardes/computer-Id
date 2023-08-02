package br.com.unimedceara.core.services;

import br.com.unimedceara.core.dtos.CarenciaDto;
import br.com.unimedceara.core.exceptions.NotFoundException;
import br.com.unimedceara.core.model.CartaoCliente;
import br.com.unimedceara.core.model.CartaoClientePk;
import br.com.unimedceara.core.repository.BiometryComputerIdRepository;
import br.com.unimedceara.core.repository.CartaoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartaoClienteService {
	@Autowired
	CartaoClienteRepository cartaoClienteRepository;
	
	private NotFoundException cartaoNotFound = new NotFoundException("Cartão não encontrado");
	
	/**
	 * Gera uma instância de @CartaoClientePk a partir de uma string de carteirinha.
	 * @param str
	 * @return
	 */
	public CartaoClientePk getCartaoClientePkFromString(String str) {
		if(str == null || str.length() != 17 ) return null;

		BigDecimal codUnimed = BigDecimal.valueOf(Long.valueOf(str.substring(0, 4)));
		BigDecimal numeracaoCarteira = BigDecimal.valueOf(Long.valueOf(str.substring(4, 16)));
		String dv = str.substring(16);
		
		return new CartaoClientePk(codUnimed, dv, numeracaoCarteira);
	}
	
	/**
	 * Retorna os dados de um cartão pelo número da carteirinha como string.
	 * 
	 * @see CartaoClienteService#findById(CartaoClientePk)
	 * @param carteirinha
	 * @return
	 * @throws NotFoundException Caso a carteirinha não seja válida.
	 */
	public CartaoCliente findById(String carteirinha) throws NotFoundException {
		CartaoClientePk id = getCartaoClientePkFromString(carteirinha);
		return findById(id);
	}
	
	/**
	 * Retorna os dados de um cartão através de uma instância de CartaoClientePk.
	 * 
	 * @see CartaoClienteService#findById(String)
	 * @param id
	 * @return
	 * @throws NotFoundException Caso a carteirinha não seja válida.
	 */
	public CartaoCliente findById(CartaoClientePk id) throws NotFoundException {
		CartaoCliente cartao = cartaoClienteRepository.findById(id)
				.orElseThrow(() -> cartaoNotFound);
		return cartao;
	}
	
	/**
	 * Retorna uma lista de carencias a partir da string de uma carteirinha.
	 * 
	 * @see CartaoClienteService#findCarencias(CartaoCliente)
	 * @param carteirinha
	 * @return
	 * @throws NotFoundException Caso a carteirinha não se ja válida.
	 */
	public List<CarenciaDto> findCarencias(String carteirinha) throws NotFoundException {
		CartaoClientePk id = getCartaoClientePkFromString(carteirinha);
		CartaoCliente cartao = cartaoClienteRepository.findById(id)
				.orElseThrow(() -> cartaoNotFound); 
		
		return findCarencias(cartao);
	}
	
	/**
	 * Retorna a lista de carências a partir de um objeto CartaoCliente.
	 * 
	 * @see CartaoClienteService#findCarencias(String)
	 * @param cartao
	 * @return
	 */
	public List<CarenciaDto> findCarencias(CartaoCliente cartao) {
		List<CarenciaDto> carencias = new ArrayList<CarenciaDto>();
		
		if( cartao.getDatCarencia01() != null || cartao.getDscCarencia01() != null)
			carencias.add(new CarenciaDto(cartao.getDatCarencia01(), cartao.getDscCarencia01()));

		if( cartao.getDatCarencia02() != null || cartao.getDscCarencia02() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia02(), cartao.getDscCarencia02()));
		
		if( cartao.getDatCarencia03() != null || cartao.getDscCarencia03() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia03(), cartao.getDscCarencia03()));
		
		if( cartao.getDatCarencia04() != null || cartao.getDscCarencia04() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia04(), cartao.getDscCarencia04()));
		
		if( cartao.getDatCarencia05() != null || cartao.getDscCarencia05() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia05(), cartao.getDscCarencia05()));
		
		if( cartao.getDatCarencia06() != null || cartao.getDscCarencia06() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia06(), cartao.getDscCarencia06()));
		
		if( cartao.getDatCarencia07() != null || cartao.getDscCarencia07() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia07(), cartao.getDscCarencia07()));
		
		if( cartao.getDatCarencia08() != null || cartao.getDscCarencia08() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia08(), cartao.getDscCarencia08()));
		
		if( cartao.getDatCarencia09() != null || cartao.getDscCarencia09() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia09(), cartao.getDscCarencia09()));
		
		if( cartao.getDatCarencia10() != null || cartao.getDscCarencia10() != null)
					carencias.add(new CarenciaDto(cartao.getDatCarencia10(), cartao.getDscCarencia10()));
		
		return carencias;
	}
	
	/**
	 * Retorna o código da unimed a partir de uma string de carteirinha.
	 * 
	 * @param carteiraCompleta
	 * @return nulo se a carteira for inválida.
	 */
	public BigDecimal getCodUnimed(String carteiraCompleta) {
		return getCartaoClientePkFromString(carteiraCompleta).getCodUnimed();
	}

	/**
	 * Retorna o código da carteira a partir de uma string de carteirinha.
	 * 
	 * @param carteiraCompleta
	 * @return nulo se a carteira for inválida.
	 */
	public BigDecimal getCodCarteira(String carteiraCompleta) {
		return getCartaoClientePkFromString(carteiraCompleta).getCodCarteira();
	}

	/**
	 * Retorna o dv a partir de uma string de carteirinha.
	 * 
	 * @param carteiraCompleta
	 * @return nulo se a carteira for inválida.
	 */
	public String getDv(String carteiraCompleta) {
		return getCartaoClientePkFromString(carteiraCompleta).getDvCarteira();
	}
}
