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
import timber.log.Timber
import java.lang.Exception

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) :
    ViewModel() {
    fun getNearestRestaurants(
        currentLatitude: Double,
        currentLongitude: Double,
        viewportBounds: LatLngBounds
    ): LiveData<Lce<List<Restaurant>>> {
        Timber.d("Fetching restaurants within bounds $viewportBounds")
        var liveData = MutableLiveData<Lce<List<Restaurant>>>()
        liveData.postValue(Lce.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                nearestRestaurantsUseCase.getNearestRestaurants(
                    currentLatitude,
                    currentLongitude
                ).collect {
                    val restaurantsWithinBounds = it.filter {
                        viewportBounds.contains(LatLng(currentLatitude, currentLongitude))
                    }
                    liveData.postValue(Lce.Success(it))
                }
            } catch (e: Exception) {
                liveData.postValue(Lce.Error(e))
            }

        }
        return liveData
    }
}
