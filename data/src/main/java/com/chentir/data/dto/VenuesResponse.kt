package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class VenuesResponse(
    @SerializedName("venues") val venues: List<Venue>
)
