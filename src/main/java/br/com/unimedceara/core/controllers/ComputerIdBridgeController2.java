//package br.com.unimedceara.core.controllers;
//
//import br.com.unimedceara.core.exceptions.BadRequestException;
//import br.com.unimedceara.core.exceptions.NotFoundException;
//import br.com.unimedceara.core.model.CartaoClientePk;
//import br.com.unimedceara.core.services.BridgeService2;
//import br.com.unimedceara.core.services.CartaoClienteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/java/api/biometry/")
//public class ComputerIdBridgeController2 {
//	@Autowired
//	BridgeService2 bridgeService;
//	@Autowired
//	CartaoClienteService cartaoClienteService;
//	@GetMapping(value = "v2/verify/{card}/card", produces = "application/Json")
//	public ResponseEntity<Object> verifica(
//			@PathVariable String card){
//		CartaoClientePk cartaoCliente = cartaoClienteService.getCartaoClientePkFromString(card);
//		if(cartaoCliente == null) throw new BadRequestException("Carteira Inválida");
//		try {
//			cartaoClienteService.findById(cartaoCliente);
//		} catch (NotFoundException e) {
//			throw new BadRequestException(e.getMessage());
//		}
//		return bridgeService.existsBiometry(card);
//	}
//
//
//	@PostMapping(value = "v2/register/{card}/card")
//	public ResponseEntity<Object> createBiometry(
//			@PathVariable String card, @Param("name") String name, @Param("base64Image") String base64Image){
//		CartaoClientePk cartaoCliente = cartaoClienteService.getCartaoClientePkFromString(card);
//		if(cartaoCliente == null) throw new BadRequestException("Carteira Inválida");
//		try {
//			cartaoClienteService.findById(cartaoCliente);
//		} catch (NotFoundException e) {
//			throw new BadRequestException(e.getMessage());
//		}
//
//		return bridgeService.createBiometry(card, name, base64Image);
//	}
//
//	@PostMapping(value = "v2/update/{card}/card")
//	public ResponseEntity<Object> updateBiometry(
//			@PathVariable String card, @Param("name") String name, @Param("base64Image") String base64Image){
//		CartaoClientePk cartaoCliente = cartaoClienteService.getCartaoClientePkFromString(card);
//		if(cartaoCliente == null) throw new BadRequestException("Carteira Inválida");
//		try {
//			cartaoClienteService.findById(cartaoCliente);
//		} catch (NotFoundException e) {
//			throw new BadRequestException(e.getMessage());
//		}
//
//		return bridgeService.updateBiometry(card, name, base64Image);
//	}
//
//	@DeleteMapping(value = "image/{card}/card")
//	public ResponseEntity<Object> deleteBiometry(
//			@PathVariable String card){
//		CartaoClientePk cartaoCliente = cartaoClienteService.getCartaoClientePkFromString(card);
//		if(cartaoCliente == null) throw new BadRequestException("Carteira Inválida");
//		try {
//			cartaoClienteService.findById(cartaoCliente);
//		} catch (NotFoundException e) {
//			throw new BadRequestException(e.getMessage());
//		}
//		return bridgeService.deleteBiometry(card);
//	}
//
//}
