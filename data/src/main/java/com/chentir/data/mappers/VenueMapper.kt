package com.chentir.data.mappers

import com.chentir.data.dto.Venue
import com.chentir.domain.entities.Restaurant

// TODO
fun Venue.toRestaurant(): Restaurant =
    Restaurant()
