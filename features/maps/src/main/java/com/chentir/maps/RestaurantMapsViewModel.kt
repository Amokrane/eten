package com.chentir.maps

import androidx.lifecycle.*
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) :
    ViewModel() {
    fun getNearestRestaurants(lat: Double, lng: Double): LiveData<List<Restaurant>> {
        var liveData = MutableLiveData<List<Restaurant>>()
        viewModelScope.launch(Dispatchers.IO) {
            nearestRestaurantsUseCase.getNearestRestaurants(lat, lng).collect {
                liveData.postValue(it)
            }
        }
        return liveData
    }
}
