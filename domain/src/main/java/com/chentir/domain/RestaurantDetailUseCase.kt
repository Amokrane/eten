package com.chentir.domain

import com.chentir.core.OpenForTesting
import com.chentir.domain.entities.RestaurantDetail
import kotlinx.coroutines.flow.Flow

@OpenForTesting
class RestaurantDetailUseCase(private val restaurantDetailService: RestaurantDetailService) {
    suspend fun fetchRestaurantDetail(restaurantId: String): Flow<RestaurantDetail> =
        restaurantDetailService.fetchRestaurantDetail(restaurantId)
}
