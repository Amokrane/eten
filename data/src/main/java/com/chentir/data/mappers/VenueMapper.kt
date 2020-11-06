package com.chentir.data.mappers

import com.chentir.data.dto.Venue
import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant

fun Venue.toRestaurant(): Restaurant =
    Restaurant(this.id, this.name, this.location.address, LatLng(this.location.lat, this.location.lng))
