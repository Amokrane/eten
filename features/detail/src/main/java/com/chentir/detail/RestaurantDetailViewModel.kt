package com.chentir.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chentir.domain.RestaurantDetailUseCase
import com.chentir.domain.entities.RestaurantDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class RestaurantDetailViewModel(private val restaurantDetailUseCase: RestaurantDetailUseCase) : ViewModel() {
    fun fetchRestaurantDetail(restaurantId: String): LiveData<RestaurantDetail> {
        var liveData = MutableLiveData<RestaurantDetail>()
        viewModelScope.launch(Dispatchers.IO) {
            restaurantDetailUseCase.fetchRestaurantDetail(restaurantId).collect {
                try {
                    liveData.postValue(it)
                } catch (e: Exception) {
                    // todo: handle error
                }

            }
        }
        return liveData
    }
}
