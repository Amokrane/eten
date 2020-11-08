package com.chentir.data.dto

import com.chentir.core.OpenForTesting
import com.google.gson.annotations.SerializedName

@OpenForTesting
data class Venue(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("location") val location: Location,
    @SerializedName("canonicalUrl") val canonicalUrl: String?,
    @SerializedName("price") val price: Price?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("contact") val contact: Contact?
)
