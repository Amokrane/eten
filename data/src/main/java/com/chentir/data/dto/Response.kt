package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("venues") val venues: List<Venue>
)
