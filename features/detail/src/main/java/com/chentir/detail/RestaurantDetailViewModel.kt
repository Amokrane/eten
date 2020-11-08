package com.chentir.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chentir.core.Lce
import com.chentir.domain.RestaurantDetailUseCase
import com.chentir.domain.entities.RestaurantDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class RestaurantDetailViewModel(private val restaurantDetailUseCase: RestaurantDetailUseCase) :
    ViewModel() {
    var liveData = MutableLiveData<Lce<RestaurantDetail>>()

    fun fetchRestaurantDetail(restaurantId: String) =
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(Lce.Loading)
            restaurantDetailUseCase.fetchRestaurantDetail(restaurantId).collect {
                try {
                    liveData.postValue(Lce.Success(it))
                } catch (e: Exception) {
                    liveData.postValue(Lce.Error(e))
                }

            }
        }
}
