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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantMapsFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: RestaurantMapsViewModel by sharedViewModel()

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                showNearestRestaurants()
            } else {
                Timber.d("Permission not granted")
            }
        }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        fun newInstance() = RestaurantMapsFragment()
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

    override fun onMapReady(p0: GoogleMap?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val locationPermissionStatus = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        when (locationPermissionStatus) {
            PERMISSION_GRANTED -> showNearestRestaurants()
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
    private fun showNearestRestaurants() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            Timber.d("Current location $location")
            val liveData =
                viewModel.getNearestRestaurants("${location.latitude}", "${location.longitude}")
            liveData.observe(viewLifecycleOwner, Observer<List<Restaurant>> {
                Timber.d("Nearest Restaurants: $it")
            })
        }
    }
}
