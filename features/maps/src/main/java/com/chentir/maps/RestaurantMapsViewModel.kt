package com.chentir.maps

import androidx.lifecycle.*
import com.chentir.core.Lce
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) : ViewModel() {
    suspend fun getNearestRestaurants(lat: String, lng: String): LiveData<List<Restaurant>> {
        return nearestRestaurantsUseCase.getNearestRestaurants(lat, lng).asLiveData()
    }
}
