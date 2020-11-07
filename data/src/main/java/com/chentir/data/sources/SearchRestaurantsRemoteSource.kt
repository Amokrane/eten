package com.chentir.data.sources

import com.chentir.data.mappers.toRestaurant
import com.chentir.data.api.FoursquareAPI
import com.chentir.domain.SearchRestaurantsSource
import com.chentir.domain.entities.Restaurant

class SearchRestaurantsRemoteSource(private val foursquareAPI: FoursquareAPI) : SearchRestaurantsSource {
    override suspend fun searchRestaurants(lat: Double, lng: Double): List<Restaurant> {
        val venuesResult =
            foursquareAPI.searchVenues("$lat,$lng")
        return venuesResult.response.venues.map {
            it.toRestaurant()
        }
    }
}
