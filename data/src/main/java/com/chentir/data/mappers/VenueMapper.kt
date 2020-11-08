package com.chentir.data.mappers

import com.chentir.data.dto.Venue
import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant
import com.chentir.domain.entities.RestaurantDetail

fun Venue.toRestaurant(): Restaurant =
    Restaurant(
        this.id,
        this.name,
        this.location.address,
        LatLng(this.location.lat, this.location.lng)
    )


fun Venue.toRestaurantDetail(): RestaurantDetail =
    RestaurantDetail(
        this.id,
        this.name,
        this.location.address,
        this.location.formattedAddress,
        LatLng(this.location.lat, this.location.lng),
        this.price!!.tier,
        this.rating!!,
        this.contact!!.formattedPhone
    )
