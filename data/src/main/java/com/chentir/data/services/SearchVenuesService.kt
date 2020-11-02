package com.chentir.data.services

import com.chentir.data.dto.VenuesResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchVenuesService {
    @GET("search")
    suspend fun searchVenues(
        @Query("ll") ll: String
    ): VenuesResult
}
