package com.chentir.maps.di

import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.maps.RestaurantMapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantMapsModule = module {
    viewModel {
        val nearestRestaurantsUseCase: NearestRestaurantsUseCase = get()
        RestaurantMapsViewModel(nearestRestaurantsUseCase)
    }
}
