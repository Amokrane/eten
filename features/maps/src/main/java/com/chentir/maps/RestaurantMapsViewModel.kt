package com.chentir.maps

import androidx.lifecycle.*
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.Restaurant
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) :
    ViewModel() {
    fun getNearestRestaurants(
        currentLatitude: Double,
        currentLongitude: Double,
        viewportBounds: LatLngBounds
    ): LiveData<List<Restaurant>> {
        Timber.d("Fetching restaurants within bounds $viewportBounds")
        var liveData = MutableLiveData<List<Restaurant>>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                nearestRestaurantsUseCase.getNearestRestaurants(
                    currentLatitude,
                    currentLongitude
                ).collect {
                    val restaurantsWithinBounds = it.filter {
                        viewportBounds.contains(LatLng(currentLatitude, currentLongitude))
                    }
                    liveData.postValue(restaurantsWithinBounds)
                }
            } catch (e: Exception) {
                // todo: handle error
            }

        }
        return liveData
    }
}
