package com.chentir.data.sources

import com.chentir.data.api.FoursquareAPI
import com.chentir.data.mappers.toRestaurantDetail
import com.chentir.domain.RestaurantDetailSource
import com.chentir.domain.entities.RestaurantDetail

class RestaurantDetailRemoteSource(private val foursquareAPI: FoursquareAPI) : RestaurantDetailSource {
    override suspend fun fetchRestaurantDetail(restaurantId: String): RestaurantDetail =
        foursquareAPI.fetchVenue(restaurantId).response.venue.toRestaurantDetail()
}
