package com.chentir.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.chentir.domain.entities.Restaurant
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.OnMapReadyCallback
import com.google.android.libraries.maps.SupportMapFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantMapsFragment : Fragment(), OnMapReadyCallback {
    private val viewModel: RestaurantMapsViewModel by sharedViewModel()

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

        val liveData = viewModel.getNearestRestaurants("48.904474", "2.072183")
        liveData.observe(viewLifecycleOwner, Observer<List<Restaurant>> {
                Timber.d("Nearest Restaurants: $it")
            })
    }

    override fun onMapReady(p0: GoogleMap?) {
        Timber.d("onMapReady called")
    }
}
