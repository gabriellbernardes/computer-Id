package br.com.unimedceara.core.model.api

import com.google.gson.annotations.SerializedName

class BodyApi (
    @SerializedName("fotob64") val base64Image: String? = null,
    @SerializedName("idPessoa") val idPessoa: String? = null,
    @SerializedName("data") val data: DataApi? = null
)