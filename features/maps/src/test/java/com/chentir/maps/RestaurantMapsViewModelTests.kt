package com.chentir.maps

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chentir.domain.NearestRestaurantsUseCase
import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant
import com.google.android.gms.maps.model.LatLngBounds
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RestaurantMapsViewModelTests {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    companion object {
         val FIRST_RESTAURANT = Restaurant(
            id = "fake_first_restaurant_id",
            name = "fake_first_restaurant_name",
            address = "fake_first_restaurant_address",
            latlng = LatLng(48.85998512078513, 2.3607351500162)
        )

        val SECOND_RESTAURANT = Restaurant(
            id = "fake_second_restaurant_id",
            name = "fake_second_restaurant_name",
            address = "fake_second_restaurant_address",
            latlng = LatLng(48.875161, 2.348226)
        )

        val THIRD_RESTAURANT = Restaurant(
            id = "fake_third_restaurant_id",
            name = "fake_third_restaurant_name",
            address = "fake_third_restaurant_address",
            latlng = LatLng(48.848332, 2.259869)
        )

        val FOURTH_RESTAURANT = Restaurant(
            id = "fake_fourth_restaurant_id",
            name = "fake_fourth_restaurant_name",
            address = "fake_fourth_restaurant_address",
            latlng = LatLng(48.86358902223995, 2.287205457687378)
        )
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var nearestRestaurantsUseCase: NearestRestaurantsUseCase

    @InjectMocks
    private lateinit var restaurantMapsViewModel: RestaurantMapsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getNearestRestaurants_givenRestaurantsWithinAndOutsideBounds_OnlyRestaurantsWithinBoundsShouldBePosted() {
        runBlocking {
            launch (Dispatchers.Main) {
                val currentLat = 48.859985
                val currentLng = 2.360735

                val latLngBounds = LatLngBounds.Builder().include(
                    com.google.android.gms.maps.model.LatLng(
                        48.82161289997943,
                        2.3270265385508537
                    )
                ).include(
                    com.google.android.gms.maps.model.LatLng(
                        48.89832727961547, 2.394442856311798
                    )
                ).build()

                whenever(
                    nearestRestaurantsUseCase.getNearestRestaurants(
                        currentLat,
                        currentLng
                    )
                ).thenReturn(
                    flowOf(listOf(
                        FIRST_RESTAURANT,
                        SECOND_RESTAURANT,
                        THIRD_RESTAURANT,
                        FOURTH_RESTAURANT
                    ))
                )


                restaurantMapsViewModel.getNearestRestaurants(currentLat, currentLng, latLngBounds).join()
                val liveData = restaurantMapsViewModel.liveData

                assertEquals(listOf(FIRST_RESTAURANT, SECOND_RESTAURANT), liveData.value!!.data)
            }
        }
    }
}
