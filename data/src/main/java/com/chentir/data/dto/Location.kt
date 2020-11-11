package com.chentir.data.dto

import com.chentir.core.OpenForTesting
import com.google.gson.annotations.SerializedName

@OpenForTesting
data class Location(
    @SerializedName("address") val address: String?,
    @SerializedName("formattedAddress") val formattedAddress: List<String>?,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)

