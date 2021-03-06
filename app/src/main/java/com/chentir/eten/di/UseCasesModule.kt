package com.chentir.eten.di

import com.chentir.data.repositories.RestaurantDetailRepository
import com.chentir.data.repositories.SearchRestaurantsRepository
import com.chentir.data.api.FoursquareAPI
import com.chentir.data.caching.RestaurantsMemoryCache
import com.chentir.data.sources.RestaurantDetailRemoteSource
import com.chentir.data.sources.SearchRestaurantsRemoteSource
import com.chentir.domain.*
import com.chentir.domain.utils.GeoUtils
import org.koin.dsl.module

val nearestRestaurantsUseCasesModule = module {
    single<SearchRestaurantsSource> {
        val foursquareAPI: FoursquareAPI = get()
        SearchRestaurantsRemoteSource(foursquareAPI)
    }

    single<RestaurantsCache> {
        RestaurantsMemoryCache()
    }

    single {
        val searchRestaurantsSource: SearchRestaurantsSource = get()
        val restaurantsCache: RestaurantsCache = get()
        SearchRestaurantsRepository(searchRestaurantsSource, restaurantsCache)
    }

    single {
        GeoUtils()
    }

    single {
        val searchRestaurantsRepository: SearchRestaurantsRepository = get()
        val geoUtils: GeoUtils = get()
        NearestRestaurantsUseCase(searchRestaurantsRepository, geoUtils)
    }
}

val restaurantDetailUseCasesModule = module {
    single<RestaurantDetailSource> {
        val foursquareAPI: FoursquareAPI = get()
        RestaurantDetailRemoteSource(foursquareAPI)
    }

    single {
        val restaurantDetailSource: RestaurantDetailSource = get()
        RestaurantDetailRepository(restaurantDetailSource)
    }

    single {
        val restaurantDetailRepository: RestaurantDetailRepository = get()
        RestaurantDetailUseCase(restaurantDetailRepository)
    }
}
