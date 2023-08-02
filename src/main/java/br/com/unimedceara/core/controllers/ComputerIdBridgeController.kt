package br.com.unimedceara.core.controllers

import br.com.unimedceara.core.dtos.BiometryDTO
import br.com.unimedceara.core.exceptions.BadRequestException
import br.com.unimedceara.core.exceptions.NotFoundException
import br.com.unimedceara.core.services.BridgeService
import br.com.unimedceara.core.services.CartaoClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/biometry/")
class ComputerIdBridgeController {
    @Autowired
    var bridgeService: BridgeService? = null

    @Autowired
    var cartaoClienteService: CartaoClienteService? = null
    @GetMapping(value = ["v2/verify/{card}/card"], produces = ["application/Json"])
    fun verifyBiometry(
            @PathVariable card: String): ResponseEntity<Any> {
        verficaCarteira(card)
        return bridgeService!!.existsBiometry(card)
    }
    @GetMapping(value = ["image/{card}/card"], produces = ["application/Json"])
    fun getImageBiometry(
        @PathVariable card: String): ResponseEntity<Any> {
        verficaCarteira(card)
        return bridgeService!!.getImageBiometry(card)
    }
    @PostMapping(value = ["register/{card}/card"], produces = ["application/Json"])
    fun createBiometry(
            @PathVariable card: String, @RequestBody body: BiometryDTO): ResponseEntity<Any> {
        verficaCarteira(card)
        return bridgeService!!.createBiometry(card, body.name!!, body.base64Image!!)
    }

    @PostMapping(value = ["validate/{card}/card"], produces = ["application/Json"])
    fun validateBiometry(
        @PathVariable card: String, @RequestBody body: BiometryDTO): ResponseEntity<Any> {
        verficaCarteira(card)
        return bridgeService!!.validateBiometry(card, body.name!!, body.base64Image!!)
    }

    @PostMapping(value = ["v2/update/{card}/card"])
    fun updateBiometry(
            @PathVariable card: String, @Param("name") name: String?, @Param("base64Image") base64Image: String?): ResponseEntity<Any> {
        verficaCarteira(card)
        return bridgeService!!.updateBiometry(card, name, base64Image)
    }

    @DeleteMapping(value = ["image/{card}/card"], produces = ["application/Json"])
    fun deleteBiometry(
            @PathVariable card: String): ResponseEntity<Any> {
        verficaCarteira(card)
        return bridgeService!!.deleteBiometry(card)
    }

    private fun verficaCarteira(card:String){
        val cartaoCliente = cartaoClienteService!!.getCartaoClientePkFromString(card)
            ?: throw BadRequestException("Carteira Inválida")
        try {
            cartaoClienteService!!.findById(cartaoCliente)
        } catch (e: NotFoundException) {
            throw BadRequestException(e.message)
        }
    }
}