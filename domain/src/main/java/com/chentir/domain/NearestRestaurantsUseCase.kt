package com.chentir.domain

import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant
import com.chentir.domain.utils.GeoUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class NearestRestaurantsUseCase(
    private val searchRestaurantsRepository: SearchRestaurantsRepository,
    private val geoUtils: GeoUtils
) {
    companion object {
        const val DEFAULT_RADIUS_IN_METERS = 1_000
    }

    suspend fun getNearestRestaurants(lat: Double, lng: Double): Flow<List<Restaurant>> {
        val p0 = LatLng(lat, lng)
        return searchRestaurantsRepository.searchRestaurants(lat, lng).map {
            it.filter {
                restaurant ->
                val distance = geoUtils.calculateDistanceInMeters(p0, restaurant.latlng)
                Timber.d("Distance between $p0 and $restaurant is $distance")
                distance <= DEFAULT_RADIUS_IN_METERS
            }
        }
    }
}
