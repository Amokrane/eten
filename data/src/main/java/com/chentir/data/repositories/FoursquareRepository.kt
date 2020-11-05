package com.chentir.data.repositories

import com.chentir.data.mappers.toRestaurant
import com.chentir.data.services.SearchVenuesService
import com.chentir.data.utils.ResponseHandler
import com.chentir.domain.SearchRestaurantsRepository
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.lang.Exception

class FoursquareRepository(private val searchVenuesService: SearchVenuesService) : SearchRestaurantsRepository {
    override suspend fun searchRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>> =
        flow {
            try {
                val venuesResult =
                    searchVenuesService.searchVenues("$lat,$lng")
                ResponseHandler.handleSuccess(venuesResult.response.venues)
                val restaurants: List<Restaurant> = venuesResult.response.venues.map {
                    it.toRestaurant()
                }
                emit(restaurants)
            } catch (e: Exception) {
                emit(listOf())
            }
        }
}
