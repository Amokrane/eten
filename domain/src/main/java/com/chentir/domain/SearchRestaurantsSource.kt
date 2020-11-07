package com.chentir.domain

import com.chentir.domain.entities.Restaurant

interface SearchRestaurantsSource {
    suspend fun searchRestaurants(lat: Double, lng: Double): List<Restaurant>
}
