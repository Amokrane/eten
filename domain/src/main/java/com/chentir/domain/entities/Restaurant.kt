package com.chentir.domain.entities

import com.chentir.core.OpenForTesting

@OpenForTesting
data class Restaurant(
    val id: String,
    val name: String,
    val address: String?,
    val latlng: LatLng
)
