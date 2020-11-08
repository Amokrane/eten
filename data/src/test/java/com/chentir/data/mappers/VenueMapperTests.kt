package com.chentir.data.mappers

import com.chentir.data.dto.Contact
import com.chentir.data.dto.Location
import com.chentir.data.dto.Venue
import com.chentir.domain.entities.LatLng
import com.chentir.domain.entities.Restaurant
import com.chentir.domain.entities.RestaurantDetail
import org.junit.Assert.assertEquals
import org.junit.Test

class VenueMapperTests {
    @Test
    fun toRestaurant() {
        val venue = givenVenue()

        assertEquals(venue.toRestaurant(), Restaurant(
            id = "fake_id",
            name = "fake_name",
            address = "fake_address",
            latlng = LatLng(48.0, 2.0)
        ))
    }

    @Test
    fun toRestaurantDetail() {
        val venue = givenVenue()

        assertEquals(venue.toRestaurantDetail(), RestaurantDetail(
            id = "fake_id",
            name = "fake_name",
            address = "fake_address",
            formattedAddress = arrayOf("fake, formatted, address"),
            latLng = LatLng(48.0, 2.0),
            phoneNumber = "fake_formatted_phone_number"
        ))
    }

    private fun givenVenue() = Venue(
        id = "fake_id",
        name = "fake_name",
        location = Location("fake_address", arrayOf("fake, formatted, address"), 48.0, 2.0),
        canonicalUrl = "canonical_url",
        contact = Contact("fake_phone_number", "fake_formatted_phone_number")
    )
}
