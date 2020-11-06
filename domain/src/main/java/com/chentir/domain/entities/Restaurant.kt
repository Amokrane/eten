package com.chentir.domain.entities

data class Restaurant(
    val id: String,
    val name: String,
    val address: String?,
    val latlng: LatLng
)
