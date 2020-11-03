package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class VenuesResult(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: Response
)