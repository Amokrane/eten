package com.chentir.data.repositories

import app.cash.turbine.test
import com.chentir.domain.RestaurantDetailSource
import com.chentir.domain.entities.RestaurantDetail
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException
import kotlin.time.ExperimentalTime

class RestaurantDetailRepositoryTests {
    @Mock
    private lateinit var restaurantDetailSource: RestaurantDetailSource

    @Mock
    private lateinit var restaurantDetail: RestaurantDetail

    private lateinit var restaurantDetailRepository: RestaurantDetailRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        restaurantDetailRepository = RestaurantDetailRepository(restaurantDetailSource)
    }

    @ExperimentalTime
    @Test
    fun fetchRestaurantDetail_NonEmptySource() {
        runBlocking {
            whenever(restaurantDetailSource.fetchRestaurantDetail("fake_restaurant_id")).thenReturn(
                restaurantDetail
            )

            val restaurantDetailFlow =
                restaurantDetailRepository.fetchRestaurantDetail("fake_restaurant_id")

            restaurantDetailFlow.test {
                assertEquals(restaurantDetail, expectItem())
                expectComplete()
            }
        }
    }

    @ExperimentalTime
    @Test
    fun fetchRestaurantDetail_Error() {
        runBlocking {
            whenever(restaurantDetailSource.fetchRestaurantDetail("fake_restaurant_id")).thenThrow(
                RuntimeException()
            )

            val restaurantDetailFlow =
                restaurantDetailRepository.fetchRestaurantDetail("fake_restaurant_id")

            restaurantDetailFlow.test {
                expectError()
            }
        }
    }
}
