package com.chentir.domain.entities

data class RestaurantDetail(
    val id: String,
    val name: String,
    val address: String?,
    val latLng: LatLng,
    val priceCategory: Int,
    val rating: Double,
    val phoneNumber: String
)
