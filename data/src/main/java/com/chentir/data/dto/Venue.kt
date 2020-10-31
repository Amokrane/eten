package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat: Long,
    @SerializedName("lng") val lng: Long
)
