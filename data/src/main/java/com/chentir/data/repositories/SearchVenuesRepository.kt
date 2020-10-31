package com.chentir.data.repositories

import com.chentir.data.Auth
import com.chentir.data.dto.Venue
import com.chentir.data.services.SearchVenuesService
import com.chentir.data.utils.Resource
import com.chentir.data.utils.ResponseHandler
import java.lang.Exception

class SearchVenuesRepository(private val searchVenuesService: SearchVenuesService) {
    suspend fun searchVenues(lat: String, lng: String, auth: Auth): Resource<List<Venue>> {
        return try {
            val venuesResult =
                searchVenuesService.searchVenues("$lat,$lng", auth.clientId, auth.clientSecret)
            ResponseHandler.handleSuccess(venuesResult.response.venues)
        } catch (e: Exception) {
            ResponseHandler.handleException(e)
        }
    }
}
