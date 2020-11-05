package com.chentir.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.chentir.domain.entities.Restaurant
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantMapsFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: RestaurantMapsViewModel by sharedViewModel()

    private lateinit var map: GoogleMap

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                showNearestRestaurants(map)
            } else {
                Timber.d("Permission not granted")
            }
        }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        fun newInstance() = RestaurantMapsFragment()
        const val DEFAULT_ZOOM_LEVEL = 13f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_maps_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        checkNotNull(map) {
            Timber.e("GoogleMap is not properly initialized.")
        }

        this.map = map

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val locationPermissionStatus = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        when (locationPermissionStatus) {
            PERMISSION_GRANTED -> showNearestRestaurants(map)
            PERMISSION_DENIED -> {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Timber.d("should show request permission rationale")
                } else {
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNearestRestaurants(map: GoogleMap) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            val currentLat = 48.859985
            val currentLng = 2.360735


            viewModel.getNearestRestaurants(currentLat, currentLng)
                .observe(viewLifecycleOwner, Observer<List<Restaurant>> { restaurants ->
                    val boundsBuilder = LatLngBounds.Builder()
                    restaurants.forEach { restaurant ->
                        boundsBuilder.include(LatLng(restaurant.latlng.lat, restaurant.latlng.lng))

                        map.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    restaurant.latlng.lat,
                                    restaurant.latlng.lng
                                )
                            ).title(restaurant.name)
                        )
                    }
                    val bounds = boundsBuilder.build()
                    map.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            bounds.center,
                            DEFAULT_ZOOM_LEVEL
                        )
                    )
                })
        }
    }
}
