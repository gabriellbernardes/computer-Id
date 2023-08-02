package br.com.unimedceara.core.services.api

import br.com.unimedceara.core.model.api.BodyApi
import br.com.unimedceara.core.model.api.DataApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ComputerIdBridgeClient {
    @POST("wsfacecid/v2/verify")
    fun verifyFace(
        @Body body: BodyApi?
    ): Call<ResponseBody>

    @GET("wsfacecid/v2/exists/{idPessoa}")
    fun exists(
        @Path("idPessoa") id: String?
    ): Call<ResponseBody>


    @POST("wsfacecid/v2/register")
    fun createBiometry(
        @Body body: BodyApi?
    ): Call<ResponseBody>

    @FormUrlEncoded
    @GET("wsfacecid/v2/register")
    fun updateBiometry(
        @Field("fotob64") fot64: String?,
        @Field("idPessoa") id: String?,
        @Field("data") data: DataApi?
    ): Call<ResponseBody>

    @DELETE("wsfacecid/v2/deactivate/{idPessoa}")
    fun deleteBiometry(
        @Path("idPessoa") idPessoa: String?
    ): Call<ResponseBody>

}