package com.chentir.data.dto

import com.chentir.core.OpenForTesting
import com.google.gson.annotations.SerializedName

@OpenForTesting
data class Price(
    @SerializedName("tier") val tier: Int,
    @SerializedName("message") val message: String
)
