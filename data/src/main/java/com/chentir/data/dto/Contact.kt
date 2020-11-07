package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("phone") val phone: String,
    @SerializedName("formattedPhone") val formattedPhone: String
)
