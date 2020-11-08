package com.chentir.data.repositories

import app.cash.turbine.test
import com.chentir.domain.RestaurantsCache
import com.chentir.domain.SearchRestaurantsSource
import com.chentir.domain.entities.Restaurant
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException
import kotlin.time.ExperimentalTime

@ExperimentalTime
class SearchRestaurantRepositoryTests {
    companion object {
        const val LAT = 48.0
        const val LNG = 2.0
    }

    @Mock
    private lateinit var searchRestaurantsSource: SearchRestaurantsSource

    @Mock
    private lateinit var firstRestaurant: Restaurant

    @Mock
    private lateinit var secondRestaurant: Restaurant

    @Mock
    private lateinit var cachedRestaurant: Restaurant

    @Mock
    private lateinit var restaurantsCache: RestaurantsCache

    @InjectMocks
    private lateinit var searchRestaurantsRepository: SearchRestaurantsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun searchRestaurants_NotEmptySource_EmptyCache() {
        val restaurants = listOf(firstRestaurant, secondRestaurant)

        runBlocking {
            whenever(restaurantsCache.get()).thenReturn(listOf())
            whenever(searchRestaurantsSource.searchRestaurants(LAT, LNG)).thenReturn(
                listOf(
                    firstRestaurant,
                    secondRestaurant
                )
            )

            val restaurantsFlow = searchRestaurantsRepository.searchRestaurants(LAT, LNG)

            restaurantsFlow.test {
                assertEquals(listOf<Restaurant>(), expectItem())
                assertEquals(restaurants, expectItem())
                expectComplete()
            }
        }
    }


    @Test
    fun searchRestaurants_NotEmptySource_NonEmptyCache() {
        val restaurants = listOf(firstRestaurant, secondRestaurant)

        runBlocking {
            whenever(restaurantsCache.get()).thenReturn(listOf(cachedRestaurant))
            whenever(searchRestaurantsSource.searchRestaurants(LAT, LNG)).thenReturn(
                listOf(
                    firstRestaurant,
                    secondRestaurant
                )
            )

            val restaurantsFlow = searchRestaurantsRepository.searchRestaurants(LAT, LNG)

            restaurantsFlow.test {
                assertEquals(listOf(cachedRestaurant), expectItem())
                assertEquals(restaurants, expectItem())
                expectComplete()
            }
        }
    }


    @Test
    fun searchRestaurants_EmptySource_NonEmptyCache() {
        runBlocking {
            whenever(restaurantsCache.get()).thenReturn(listOf(cachedRestaurant))
            whenever(searchRestaurantsSource.searchRestaurants(LAT, LNG)).thenReturn(listOf())

            val restaurantsFlow = searchRestaurantsRepository.searchRestaurants(LAT, LNG)

            restaurantsFlow.test {
                assertEquals(listOf(cachedRestaurant), expectItem())
                assertEquals(listOf<Restaurant>(), expectItem())
                expectComplete()
            }
        }
    }

    @Test
    fun searchRestaurants_ErrorWhenReadingFromCache() {
        runBlocking {
            whenever(restaurantsCache.get()).thenThrow(RuntimeException())
            whenever(searchRestaurantsSource.searchRestaurants(LAT, LNG)).thenReturn(
                listOf(
                    firstRestaurant,
                    secondRestaurant
                )
            )

            val restaurantsFlow = searchRestaurantsRepository.searchRestaurants(LAT, LNG)

            restaurantsFlow.test {
                expectError()
            }
        }
    }

    @Test
    fun searchRestaurants_ErrorWhenReadingFromSource() {
        runBlocking {
            whenever(restaurantsCache.get()).thenReturn(listOf(cachedRestaurant))
            whenever(searchRestaurantsSource.searchRestaurants(LAT, LNG)).thenThrow(RuntimeException())

            val restaurantsFlow = searchRestaurantsRepository.searchRestaurants(LAT, LNG)

            restaurantsFlow.test {
                assertEquals(listOf(cachedRestaurant), expectItem())
                expectError()
            }
        }
    }
}
