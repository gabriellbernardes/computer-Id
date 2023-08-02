package br.com.unimedceara.core.model.api

import com.google.gson.annotations.SerializedName

class ApiResponse (
    @SerializedName("code") val code: Int,
    @SerializedName("msg") val message: String,
)