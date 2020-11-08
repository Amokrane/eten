package com.chentir.data.dto

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address") val address: String?,
    @SerializedName("formattedAddress") val formattedAddress: Array<String>?,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (address != other.address) return false
        if (formattedAddress != null) {
            if (other.formattedAddress == null) return false
            if (!formattedAddress.contentEquals(other.formattedAddress)) return false
        } else if (other.formattedAddress != null) return false
        if (lat != other.lat) return false
        if (lng != other.lng) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address?.hashCode() ?: 0
        result = 31 * result + (formattedAddress?.contentHashCode() ?: 0)
        result = 31 * result + lat.hashCode()
        result = 31 * result + lng.hashCode()
        return result
    }
}
