package com.chentir.data.dto

import com.chentir.core.OpenForTesting
import com.google.gson.annotations.SerializedName

@OpenForTesting
data class VenueResponse(
    @SerializedName("venue") val venue: Venue
)
