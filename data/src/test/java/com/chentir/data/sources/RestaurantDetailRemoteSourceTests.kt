package com.chentir.data.sources

import com.chentir.data.api.FoursquareAPI
import com.chentir.data.dto.*
import com.chentir.data.mappers.toRestaurantDetail
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RestaurantDetailRemoteSourceTests {
    @Mock
    private lateinit var foursquareAPI: FoursquareAPI

    @InjectMocks
    private lateinit var restaurantDetailRestaurantsRemoteSource: RestaurantDetailRemoteSource

    @Mock
    private lateinit var venueDetailResult: VenueDetailResult

    @Mock
    private lateinit var venueResponse: VenueResponse


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testFetchRestaurantDetail() {
        runBlocking {
            val venue = givenVenue()
            val restaurantDetail = venue.toRestaurantDetail()
            whenever(venueResponse.venue).thenReturn(venue)
            whenever(venueDetailResult.response).thenReturn(venueResponse)
            whenever(foursquareAPI.fetchVenue("fake_restaurant_id")).thenReturn(venueDetailResult)

            assertEquals(restaurantDetail, restaurantDetailRestaurantsRemoteSource.fetchRestaurantDetail("fake_restaurant_id"))
        }
    }

    private fun givenVenue(): Venue {
        val venue = mock<Venue>()
        whenever(venue.id).thenReturn("fake_venue_id")
        whenever(venue.name).thenReturn("fake_name")
        whenever(venue.canonicalUrl).thenReturn("fake_canonical_url")

        val location = mock<Location>()
        whenever(location.lat).thenReturn(48.0)
        whenever(location.lng).thenReturn(2.0)
        whenever(venue.location).thenReturn(location)

        val contact = mock<Contact>()
        whenever(contact.formattedPhone).thenReturn("fake_formatted_phone")
        whenever(venue.contact).thenReturn(contact)

        return venue
    }
}
