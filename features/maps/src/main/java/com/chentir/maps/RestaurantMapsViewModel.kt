package com.chentir.maps

import androidx.lifecycle.*
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) : ViewModel() {
    fun getNearestRestaurants(lat: String, lng: String): LiveData<List<Restaurant>> {
        var liveData: LiveData<List<Restaurant>> = MutableLiveData<List<Restaurant>>()
        viewModelScope.launch(Dispatchers.IO) {
            liveData = nearestRestaurantsUseCase.getNearestRestaurants(lat, lng).asLiveData()
        }
        return liveData
    }
}
