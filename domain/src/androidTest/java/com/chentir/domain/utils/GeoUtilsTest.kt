package com.chentir.domain.utils

import com.chentir.domain.entities.LatLng
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GeoUtilsTest {
    @Test
    fun calculateDistance() {
        val p0 = LatLng(52.374608, 4.890842)
        val p1 = LatLng(48.904495, 2.072205)

        assertEquals(434_000, GeoUtils().calculateDistanceInMeters(p0, p1))
    }
}
