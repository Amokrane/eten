package com.chentir.domain

import com.chentir.domain.entities.RestaurantDetail

interface RestaurantDetailSource {
    suspend fun fetchRestaurantDetail(restaurantId: String): RestaurantDetail
}
