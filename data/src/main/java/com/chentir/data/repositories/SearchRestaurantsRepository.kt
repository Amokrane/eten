package com.chentir.data.repositories

import com.chentir.domain.SearchRestaurantsSource
import com.chentir.domain.SearchRestaurantsService
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception

class SearchRestaurantsRepository(private val searchRestaurantsSource: SearchRestaurantsSource) :
    SearchRestaurantsService {
    override suspend fun searchRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>> =
        flow {
            try {
                val restaurants = searchRestaurantsSource.searchRestaurants(lat, lng)
                emit(restaurants)
            } catch (e: Exception) {
                Timber.e(e)
                emit(listOf())
            }
        }
}
