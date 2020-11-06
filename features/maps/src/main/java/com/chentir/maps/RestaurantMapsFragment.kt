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
import androidx.lifecycle.coroutineScope
import com.chentir.domain.entities.Restaurant
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantMapsFragment : Fragment(), OnMapReadyCallback, OnCameraMoveListener, GoogleMap.OnMarkerClickListener {
    private val viewModel: RestaurantMapsViewModel by sharedViewModel()
    private lateinit var map: GoogleMap
    private var initialAnimationFinished = false

    private val coroutineScope = lifecycle.coroutineScope
    private var fetcherJob: Job? = null

    val markerMap = mutableMapOf<Marker, Restaurant>()

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
        const val DEBOUNCE_DELAY_IN_MS = 100L
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
        with(map) {
            setOnCameraMoveListener(this@RestaurantMapsFragment)
            setOnMarkerClickListener(this@RestaurantMapsFragment)
        }

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
            // TODO: replace with location
            val currentLat = 48.859985
            val currentLng = 2.360735
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(currentLat, currentLng),
                    DEFAULT_ZOOM_LEVEL
                ), object : GoogleMap.CancelableCallback {
                    override fun onFinish() {
                        initialAnimationFinished = true
                    }

                    override fun onCancel() {
                        // nothing to be done
                    }
                }
            )
            updateNearestRestaurants(currentLat, currentLng)
        }
    }

    /**
     * To avoid being rate-limited, the following strategies are used:
     * - Only fetch restaurants when the initial animation is finished.
     * - Apply a debounce when panning the map, to only fetch restaurants when the camera position is final after a pan.
     **/
    override fun onCameraMove() {
        if (initialAnimationFinished) {
            fetcherJob?.cancel()
            fetcherJob = coroutineScope.launch {
                delay(DEBOUNCE_DELAY_IN_MS)
                updateNearestRestaurants(
                    map.cameraPosition.target.latitude,
                    map.cameraPosition.target.longitude
                )
            }
        }
    }

    private fun updateNearestRestaurants(lat: Double, lng: Double) {
        viewModel.getNearestRestaurants(lat, lng)
            .observe(viewLifecycleOwner, Observer<List<Restaurant>> { restaurants ->
                restaurants.forEach { restaurant ->
                    val marker = map.addMarker(MarkerOptions().position(
                        LatLng(
                            restaurant.latlng.lat,
                            restaurant.latlng.lng
                        )
                    ).title(restaurant.name))
                    markerMap[marker] = restaurant
                }
            })
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val restaurant = markerMap[marker]
        Timber.d("$marker marker clicked, corresponding to $restaurant")
        return true // consume the event
    }
}
