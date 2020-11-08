package com.chentir.domain

import app.cash.turbine.test
import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant
import com.chentir.domain.utils.GeoUtils
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime

@ExperimentalTime
class NearestRestaurantsUseCaseTest {
    @Mock
    private lateinit var searchRestaurantsService: SearchRestaurantsService

    @Mock
    private lateinit var geoUtils: GeoUtils

    @InjectMocks
    private lateinit var nearestRestaurantsUseCase: NearestRestaurantsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getNearestRestaurants() {
        runBlocking {
            val currentLat = 48.859985
            val currentLng = 2.360735

            val firstRestaurantLatLng = LatLng(48.861492, 2.358267)
            val firstRestaurant = Restaurant(
                id = "fake_first_restaurant_id",
                name = "fake_first_restaurant_name",
                address = "fake_first_restaurant_address",
                latlng = firstRestaurantLatLng
            )

            val secondRestaurantLatLng = LatLng(47.833078, 1.917356)
            val secondRestaurant = Restaurant(
                id = "fake_second_restaurant_id",
                name = "fake_second_restaurant_name",
                address = "fake_second_restaurant_address",
                latlng = secondRestaurantLatLng
            )

            whenever(searchRestaurantsService.searchRestaurants(currentLat, currentLng)).thenReturn(
                flowOf(listOf(firstRestaurant, secondRestaurant))
            )

            whenever(
                geoUtils.calculateDistanceInMeters(
                    LatLng(currentLat, currentLng),
                    firstRestaurantLatLng
                )
            ).thenReturn(500.0f)

            whenever(
                geoUtils.calculateDistanceInMeters(
                    LatLng(currentLat, currentLng),
                    secondRestaurantLatLng
                )
            ).thenReturn(6000.0f)

            val flow = nearestRestaurantsUseCase.getNearestRestaurants(currentLat, currentLng)
            flow.test {
                assertEquals(listOf(firstRestaurant), expectItem())
                expectComplete()
            }
        }
    }
}
