package com.chentir.data.api

import com.chentir.data.dto.VenueDetailResult
import com.chentir.data.dto.VenuesResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareAPI {
    @GET("venues/search")
    suspend fun searchVenues(
        @Query("ll") ll: String
    ): VenuesResult

    @GET("venues/{restaurantId}")
    suspend fun fetchVenue(@Path("restaurantId") restaurantId: String): VenueDetailResult
}
