package br.com.unimedceara.core.config

import br.com.unimedceara.core.services.api.ComputerIdBridgeClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var BASE_URL =  "https://gateway.apifacialid.com.br/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " +
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                        "eyJpZF9jbGllbnRlIjo2OSwiaWRfY2xpZW50ZV90" +
                        "YWdlZCI6MTEwLCJlbWFpbCI6ImluYWNpb2R1dHJhQH" +
                        "VuaW1lZGNlYXJhLmNvbS5iciIsInRhZ3MiOlsiVHJp" +
                        "YWwiXX0.Yuiaqx0DdxjRxu9uFtwGRudAE5zFmPS741EyEbOTmPk")
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: ComputerIdBridgeClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ComputerIdBridgeClient::class.java)
    }
}