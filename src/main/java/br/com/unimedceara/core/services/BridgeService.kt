package br.com.unimedceara.core.services

import br.com.unimedceara.core.config.RetrofitClient
import br.com.unimedceara.core.model.BiometryComputerId
import br.com.unimedceara.core.model.api.BodyApi
import br.com.unimedceara.core.model.api.DataApi
import br.com.unimedceara.core.repository.BiometryComputerIdRepository
import br.com.unimedceara.core.services.api.ComputerIdBridgeClient
import okhttp3.ResponseBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import retrofit2.Call
import java.io.IOException

@Service
class BridgeService @Autowired constructor(
    private val computerIdBridgeClient: ComputerIdBridgeClient,
    private val biometryComputerIdRepository: BiometryComputerIdRepository
) {
    private fun getObjectResponseEntity(callSync: Call<ResponseBody>): ResponseEntity<Any> {
        return try {
            val response = callSync.execute()
            val status = response.code()
            when {
                response.isSuccessful -> {
                    val headers = response.headers()
                    val httpHeaders = HttpHeaders()
                    for ((headerName, headerValues) in headers.toMultimap().entries ) {
                        httpHeaders.addAll(headerName, headerValues)
                    }
                    return ResponseEntity.status(status).headers(httpHeaders).body(response.body()!!.string())
                }
                else -> {
                    val error: ResponseBody? = response.errorBody() ?: response.body()
                    return ResponseEntity.status(status).body(error!!.string())
                }
            }
        } catch (e: IOException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    fun existsBiometry(card: String?): ResponseEntity<Any> {
        val callSync = computerIdBridgeClient.exists(card)
        return getObjectResponseEntity(callSync)
    }

    fun createBiometry(card: String, name: String, base64Image: String): ResponseEntity<Any> {
        val data = DataApi(additionalProp1 = name)
        val callSync = RetrofitClient.instance.createBiometry(BodyApi(base64Image, card, data))
        val result = getObjectResponseEntity(callSync)
        if (result.statusCodeValue ==   HttpStatus.OK.value() ) {
            biometryComputerIdRepository.save(BiometryComputerId(card, base64Image, name))
        }
        return result
    }

    fun updateBiometry(card: String?, name: String?, base64Image: String?): ResponseEntity<Any> {
        val data = DataApi(additionalProp1 = name)
        val callSync = computerIdBridgeClient.updateBiometry(base64Image, card, data)
        return getObjectResponseEntity(callSync)
    }

    fun deleteBiometry(card: String): ResponseEntity<Any> {
        val callSync = computerIdBridgeClient.deleteBiometry(card)
        val result = getObjectResponseEntity(callSync)
        if (result.statusCodeValue ==   HttpStatus.OK.value() ) {
            val a = biometryComputerIdRepository.findById(card)
            if (a.isPresent) {
                biometryComputerIdRepository.delete(a.get())
            }
        }
        return result
    }

    fun validateBiometry(card: String, name: String, base64Image: String): ResponseEntity<Any> {
        val data = DataApi(additionalProp1 = name)
        val callSync = computerIdBridgeClient.verifyFace(BodyApi(base64Image, card, data))
        return getObjectResponseEntity(callSync)
    }

    fun getImageBiometry(card: String): ResponseEntity<Any> {
        val a = biometryComputerIdRepository.findById(card)
        if (a.isPresent) {
            return ResponseEntity.status(200).body(a.get())
        }
        return ResponseEntity.status(404).body("Dados inexistentes")
    }
}