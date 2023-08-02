package br.com.unimedceara.core.model.api

import com.fasterxml.jackson.annotation.JsonProperty


class DataApi(
    @JsonProperty("additionalProp1")
    var additionalProp1: String? = "",

    @JsonProperty("additionalProp2")
    var additionalProp2: String? = "",

    @JsonProperty("additionalProp3")
    var additionalProp3: String? = ""
)