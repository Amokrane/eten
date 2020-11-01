package com.chentir.detail.di

import com.chentir.detail.RestaurantDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantDetailModule = module {
    viewModel {
        RestaurantDetailViewModel()
    }
}
