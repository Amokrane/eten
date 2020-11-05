package com.chentir.eten.di

import com.chentir.data.repositories.FoursquareRepository
import com.chentir.data.services.SearchVenuesService
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.utils.GeoUtils
import org.koin.dsl.module

val nearestRestaurantsUseCasesModule = module {
    single {
        val searchVenuesService: SearchVenuesService = get()
        FoursquareRepository(searchVenuesService)
    }

    single {
        GeoUtils()
    }

    single {
        val foursquareRepository: FoursquareRepository = get()
        val geoUtils: GeoUtils = get()
        NearestRestaurantsUseCase(foursquareRepository, geoUtils)
    }
}
