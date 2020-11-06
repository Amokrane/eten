package com.chentir.data.repositories

import com.chentir.domain.RestaurantsSource
import com.chentir.domain.SearchRestaurantsRepository
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FoursquareRepository(private val restaurantsSource: RestaurantsSource) :
    SearchRestaurantsRepository {
    override suspend fun searchRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>> =
        flow {
            try {
                val restaurants = restaurantsSource.searchRestaurants(lat, lng)
                emit(restaurants)
            } catch (e: Exception) {
                emit(listOf())
            }
        }
}
