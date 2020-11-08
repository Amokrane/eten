package com.chentir.domain.utils

import android.location.Location
import com.chentir.core.OpenForTesting
import com.chentir.domain.entities.LatLng

@OpenForTesting
class GeoUtils {
    fun calculateDistanceInMeters(p0: LatLng, p1: LatLng): Float {
        val results = FloatArray(1)
        Location.distanceBetween(p0.lat, p0.lng, p1.lat, p1.lng, results)
        return results[0]
    }
}
