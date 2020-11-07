package com.chentir.domain

import com.chentir.domain.entities.RestaurantDetail
import kotlinx.coroutines.flow.Flow

interface RestaurantDetailService {
    suspend fun fetchRestaurantDetail(restaurantId: String): Flow<RestaurantDetail>
}
