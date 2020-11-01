package com.chentir.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chentir.core.Lce
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantMapsViewModel(private val nearestRestaurantsUseCase: NearestRestaurantsUseCase) : ViewModel() {
    fun getNearestRestaurants(lat: String, lng: String): LiveData<Lce<List<Restaurant>>> {
        val liveData = MutableLiveData<Lce<List<Restaurant>>>()
//        viewModelScope.launch(Dispatchers.IO) {
//            val resource = searchVenuesRepository.searchVenues(lat, lng, Auth("", ""))
//            when (resource.status) {
//                Status.SUCCESS -> liveData.postValue(Lce.Success(resource.data!!))
//                Status.ERROR -> liveData.postValue(Lce.Error(resource.message!!))
//            }
//        }
        return liveData
    }
}
