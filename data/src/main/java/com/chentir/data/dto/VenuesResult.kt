package com.chentir.data.dto

import com.chentir.core.OpenForTesting
import com.google.gson.annotations.SerializedName

@OpenForTesting
data class VenuesResult(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("response") val response: VenuesResponse
)
