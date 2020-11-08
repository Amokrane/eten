package com.chentir.maps

import androidx.lifecycle.*
import com.chentir.core.Lce
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.Restaurant
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) :
    ViewModel() {
    var liveData = MutableLiveData<Lce<List<Restaurant>>>()


    fun getNearestRestaurants(
        currentLatitude: Double,
        currentLongitude: Double,
        viewportBounds: LatLngBounds
    ) = viewModelScope.launch(Dispatchers.IO) {
        liveData.postValue(Lce.Loading)
        try {
            nearestRestaurantsUseCase.getNearestRestaurants(
                currentLatitude,
                currentLongitude
            ).collect { restaurants ->
                val restaurantsWithinBounds = restaurants.filter {
                    restaurant ->  viewportBounds.contains(LatLng(restaurant.latlng.lat, restaurant.latlng.lng))
                }
                liveData.postValue(Lce.Success(restaurantsWithinBounds))
            }
        } catch (e: Exception) {
            liveData.postValue(Lce.Error(e))
        }
    }
}
