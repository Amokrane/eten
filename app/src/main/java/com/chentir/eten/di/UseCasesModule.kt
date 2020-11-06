package com.chentir.eten.di

import com.chentir.data.repositories.FoursquareRepository
import com.chentir.data.services.SearchVenuesService
import com.chentir.data.sources.FoursquareSource
import com.chentir.data.sources.LocalRestaurantsSource
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.RestaurantsSource
import com.chentir.domain.utils.GeoUtils
import org.koin.dsl.module

val nearestRestaurantsUseCasesModule = module {
    single<RestaurantsSource> {
        val searchVenuesService: SearchVenuesService = get()
//        FoursquareSource(searchVenuesService)
        LocalRestaurantsSource()
    }
    single {
        val restaurantsSource: RestaurantsSource = get()
        FoursquareRepository(restaurantsSource)
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
