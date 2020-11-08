package com.chentir.data.dto

import com.chentir.core.OpenForTesting
import com.google.gson.annotations.SerializedName

@OpenForTesting
data class Contact(
    @SerializedName("phone") val phone: String,
    @SerializedName("formattedPhone") val formattedPhone: String
)
