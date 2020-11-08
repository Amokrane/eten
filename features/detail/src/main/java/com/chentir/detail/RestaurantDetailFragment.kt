package com.chentir.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.chentir.core.Lce
import com.chentir.domain.entities.RestaurantDetail
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantDetailFragment : Fragment(), OnMapReadyCallback {
    companion object {
        const val DEFAULT_ZOOM_LEVEL = 13f
        fun newInstance() = RestaurantDetailFragment()
    }

    private val viewModel: RestaurantDetailViewModel by sharedViewModel()
    private lateinit var restaurantId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        findNavController().getBackStackEntry(R.id.restaurantDetailFragment).arguments?.let {
            restaurantId = it["restaurant_id"] as String
        }

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.restaurant_detail_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            viewModel.fetchRestaurantDetail(restaurantId)
            viewModel.liveData.observe(viewLifecycleOwner, Observer<Lce<RestaurantDetail>> { lce ->
                when (lce) {
                    is Lce.Loading -> {
                        Timber.d("Loading...")
                    }

                    is Lce.Success -> {
                        val restaurantDetail = lce.data
                        val restaurantLatitude = restaurantDetail.latLng.lat
                        val restaurantLongitude = restaurantDetail.latLng.lng

                        map.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    restaurantLatitude,
                                    restaurantLongitude
                                )
                            ).title(restaurantDetail.name)
                        )

                        map.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(restaurantLatitude, restaurantLongitude),
                                DEFAULT_ZOOM_LEVEL
                            )
                        )

                        val restaurantNameTextView =
                            view?.findViewById<TextView>(R.id.restaurant_name)
                        restaurantNameTextView?.text = restaurantDetail.name

                        val restaurantAddressTextView =
                            view?.findViewById<TextView>(R.id.restaurant_address)
                        restaurantAddressTextView?.text =
                            restaurantDetail.formattedAddress?.joinToString("") { it -> "$it\n" }

                        val restaurantRatingTextView =
                            view?.findViewById<TextView>(R.id.restaurant_price_range)
                        restaurantRatingTextView?.text = "${restaurantDetail.priceCategory}"
                    }

                    is Lce.Error -> {
                        Timber.e(lce.message)
                    }
                }
            })
        }
    }
}
