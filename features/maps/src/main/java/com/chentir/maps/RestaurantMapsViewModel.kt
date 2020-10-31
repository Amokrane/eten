package com.chentir.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chentir.core.Lce
import com.chentir.data.Auth
import com.chentir.data.dto.Venue
import com.chentir.data.repositories.SearchVenuesRepository
import com.chentir.data.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantMapsViewModel(private val searchVenuesRepository: SearchVenuesRepository) : ViewModel() {
    fun searchVenues(lat: String, lng: String): LiveData<Lce<List<Venue>>> {
        val liveData = MutableLiveData<Lce<List<Venue>>>()
        viewModelScope.launch(Dispatchers.IO) {
            val resource = searchVenuesRepository.searchVenues(lat, lng, Auth("", ""))
            when (resource.status) {
                Status.SUCCESS -> liveData.postValue(Lce.Success(resource.data!!))
                Status.ERROR -> liveData.postValue(Lce.Error(resource.message!!))
            }
        }
        return liveData
    }
}
