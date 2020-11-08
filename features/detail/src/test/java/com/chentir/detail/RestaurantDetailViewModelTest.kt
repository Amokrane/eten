package com.chentir.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chentir.domain.RestaurantDetailUseCase
import com.chentir.domain.entities.RestaurantDetail
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RestaurantDetailViewModelTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var restaurantDetailUseCase: RestaurantDetailUseCase

    @InjectMocks
    private lateinit var restaurantDetailViewModel: RestaurantDetailViewModel

    @Mock
    private lateinit var restaurantDetail: RestaurantDetail

    @get:Rule
    val rule = InstantTaskExecutorRule()

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
    fun fetchRestaurantDetail() {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(restaurantDetailUseCase.fetchRestaurantDetail("fake_restaurant_id")).thenReturn(
                    flowOf(restaurantDetail)
                )

                restaurantDetailViewModel.fetchRestaurantDetail("fake_restaurant_id").join()
                val liveData = restaurantDetailViewModel.liveData

                assertEquals(restaurantDetail, liveData.value!!.data)
            }
        }
    }
}
