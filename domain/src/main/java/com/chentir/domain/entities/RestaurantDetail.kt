package com.chentir.domain.entities

import com.chentir.core.OpenForTesting

@OpenForTesting
data class RestaurantDetail(
    val id: String,
    val name: String,
    val address: String?,
    val formattedAddress: List<String>?,
    val latLng: LatLng,
    val phoneNumber: String?
)
