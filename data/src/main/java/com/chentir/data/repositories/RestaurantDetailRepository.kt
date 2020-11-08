package com.chentir.data.repositories

import com.chentir.domain.RestaurantDetailService
import com.chentir.domain.RestaurantDetailSource
import com.chentir.domain.entities.RestaurantDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RestaurantDetailRepository(private val restaurantDetailSource: RestaurantDetailSource) :
    RestaurantDetailService {
    override suspend fun fetchRestaurantDetail(restaurantId: String): Flow<RestaurantDetail> =
        flow {
            val restaurantDetail = restaurantDetailSource.fetchRestaurantDetail(restaurantId)
            emit(restaurantDetail)
        }
}
