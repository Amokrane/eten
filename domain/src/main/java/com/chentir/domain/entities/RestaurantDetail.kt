package com.chentir.domain.entities

import com.chentir.core.OpenForTesting

@OpenForTesting
data class RestaurantDetail(
    val id: String,
    val name: String,
    val address: String?,
    val formattedAddress: Array<String>?,
    val latLng: LatLng,
    val priceCategory: Int,
    val rating: Double,
    val phoneNumber: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RestaurantDetail

        if (id != other.id) return false
        if (name != other.name) return false
        if (address != other.address) return false
        if (formattedAddress != null) {
            if (other.formattedAddress == null) return false
            if (!formattedAddress!!.contentEquals(other.formattedAddress!!)) return false
        } else if (other.formattedAddress != null) return false
        if (latLng != other.latLng) return false
        if (priceCategory != other.priceCategory) return false
        if (rating != other.rating) return false
        if (phoneNumber != other.phoneNumber) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (formattedAddress?.contentHashCode() ?: 0)
        result = 31 * result + latLng.hashCode()
        result = 31 * result + priceCategory
        result = 31 * result + rating.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        return result
    }
}
