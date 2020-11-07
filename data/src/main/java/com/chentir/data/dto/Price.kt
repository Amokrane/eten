package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("tier") val tier: Int,
    @SerializedName("message") val message: String
)
