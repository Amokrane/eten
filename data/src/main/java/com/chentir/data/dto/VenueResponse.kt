package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class VenueResponse(
    @SerializedName("venue") val venue: Venue
)
