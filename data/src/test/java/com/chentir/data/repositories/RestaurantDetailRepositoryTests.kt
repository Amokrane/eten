package com.chentir.data.repositories

import com.chentir.domain.RestaurantDetailSource
import com.chentir.domain.entities.RestaurantDetail
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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

    @Test
    fun fetchRestaurantDetail_NonEmptySource() {
        runBlocking {
            whenever(restaurantDetailSource.fetchRestaurantDetail("fake_restaurant_id")).thenReturn(
                restaurantDetail
            )

            val restaurantDetailFlow =
                restaurantDetailRepository.fetchRestaurantDetail("fake_restaurant_id")

            restaurantDetailFlow.collect {
                assertEquals(it, restaurantDetail)
            }
        }
    }
}
