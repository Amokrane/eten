package com.chentir.detail.di

import com.chentir.detail.RestaurantDetailViewModel
import com.chentir.domain.RestaurantDetailUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantDetailModule = module {
    viewModel {
        val restaurantDetailUseCase: RestaurantDetailUseCase = get()
        RestaurantDetailViewModel(restaurantDetailUseCase)
    }
}
