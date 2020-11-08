package com.chentir.data.caching


import ch.tutteli.atrium.verbs.assertThat
import com.chentir.domain.entities.Restaurant
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RestaurantsMemoryCacheTest {
    private lateinit var restaurantsMemoryCache: RestaurantsMemoryCache

    @Before
    fun setUp() {
        restaurantsMemoryCache = RestaurantsMemoryCache()
    }

    @Test
    fun `given cached restaurants, when calling get, then cached restaurants are returned`() {
        val firstRestaurant = mock<Restaurant>()
        val secondRestaurant = mock<Restaurant>()
        runBlocking {
            restaurantsMemoryCache.set(listOf(firstRestaurant, secondRestaurant))
            val restaurants: List<Restaurant> = restaurantsMemoryCache.get()
            assertThat(restaurants.size == 2)
            assertThat(restaurants.contains(firstRestaurant))
            assertThat(restaurants.contains(secondRestaurant))
        }
    }

    @Test
    fun `given cached restaurant, when calling get, then cached restaurant is returned`() {
        val restaurant  = mock<Restaurant>()
        runBlocking {
            restaurantsMemoryCache.add(restaurant)
            val restaurants: List<Restaurant> = restaurantsMemoryCache.get()
            assertThat(restaurants.size == 1)
            assertThat(restaurants.contains(restaurant))
        }
    }

    @Test
    fun `given no cached restaurants, when calling get, then no restaurants are returned`() {
        runBlocking {
            val restaurants: List<Restaurant> = restaurantsMemoryCache.get()
            assertThat(restaurants.isEmpty())
        }
    }
}
