package com.chentir.domain

import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow

interface SearchRestaurantsRepository {
    suspend fun searchRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>>
}
