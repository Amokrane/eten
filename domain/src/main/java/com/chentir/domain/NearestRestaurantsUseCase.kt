package com.chentir.domain

import com.chentir.core.OpenForTesting
import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant
import com.chentir.domain.utils.GeoUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OpenForTesting
class NearestRestaurantsUseCase(
    private val searchRestaurantsService: SearchRestaurantsService,
    private val geoUtils: GeoUtils
) {
    companion object {
        const val DEFAULT_RADIUS_IN_METERS = 20_000
    }

    suspend fun getNearestRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>> {
        val position = LatLng(lat, lng)
        return searchRestaurantsService.searchRestaurants(lat, lng).map {
            it.filter { restaurant ->
                val distance = geoUtils.calculateDistanceInMeters(position, restaurant.latlng)
                distance <= DEFAULT_RADIUS_IN_METERS
            }
        }
    }
}
