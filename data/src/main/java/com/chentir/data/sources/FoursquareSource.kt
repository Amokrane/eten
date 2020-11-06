package com.chentir.data.sources

import com.chentir.data.mappers.toRestaurant
import com.chentir.data.services.SearchVenuesService
import com.chentir.domain.RestaurantsSource
import com.chentir.domain.entities.Restaurant

class FoursquareSource(private val searchVenuesService: SearchVenuesService) : RestaurantsSource {
    override suspend fun searchRestaurants(lat: Double, lng: Double): List<Restaurant> {
        val venuesResult =
            searchVenuesService.searchVenues("$lat,$lng")
        return venuesResult.response.venues.map {
            it.toRestaurant()
        }
    }
}
