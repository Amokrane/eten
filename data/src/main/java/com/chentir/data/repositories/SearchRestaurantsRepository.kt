package com.chentir.data.repositories

import com.chentir.domain.RestaurantsCache
import com.chentir.data.caching.RestaurantsMemoryCache
import com.chentir.domain.SearchRestaurantsSource
import com.chentir.domain.SearchRestaurantsService
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.lang.Exception

class SearchRestaurantsRepository(
    private val searchRestaurantsSource: SearchRestaurantsSource,
    private val restaurantsCache: RestaurantsCache
) : SearchRestaurantsService {
    override suspend fun searchRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>> =
        flow {
            emit(restaurantsCache.get())
            val restaurants = searchRestaurantsSource.searchRestaurants(lat, lng)
            emit(restaurants)
            restaurantsCache.set(restaurants)
        }
}
