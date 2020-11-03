package com.chentir.domain.entities

data class Restaurant(
    val name: String,
    val address: String?,
    val latlng: LatLng
)
