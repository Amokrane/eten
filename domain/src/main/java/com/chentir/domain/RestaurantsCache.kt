package com.chentir.domain

import com.chentir.domain.entities.Restaurant

interface RestaurantsCache {
    suspend fun get(): List<Restaurant>

    suspend fun set(restaurants: List<Restaurant>)

    suspend fun add(restaurant: Restaurant)
}
