package com.chentir.data.caching

import com.chentir.domain.RestaurantsCache
import com.chentir.domain.entities.Restaurant

class RestaurantsMemoryCache : RestaurantsCache {
    private var restaurants: MutableList<Restaurant> = mutableListOf()

    override suspend fun get(): List<Restaurant> = restaurants

    override suspend fun set(restaurants: List<Restaurant>) {
        this.restaurants.addAll(restaurants)
    }

    override suspend fun add(restaurant: Restaurant) {
        restaurants.add(restaurant)
    }
}
