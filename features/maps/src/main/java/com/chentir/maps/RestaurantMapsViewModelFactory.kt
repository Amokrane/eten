package com.chentir.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chentir.data.repositories.SearchVenuesRepository

class RestaurantMapsViewModelFactory(private val searchVenuesRepository: SearchVenuesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantMapsViewModel::class.java)) {
            return RestaurantMapsViewModel(searchVenuesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
