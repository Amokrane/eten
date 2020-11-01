package com.chentir.domain

import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow

class NearestRestaurantsUseCase(private val searchRestaurantsRepository: SearchRestaurantsRepository) {
    suspend fun getNearestRestaurants(lat: String, lng: String): Flow<List<Restaurant>> =
        searchRestaurantsRepository.searchRestaurants(lat, lng)
}
