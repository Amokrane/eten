package com.chentir.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.chentir.domain.entities.Restaurant
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantMapsFragment : Fragment() {
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

        viewModel.getNearestRestaurants("48.904474", "2.072183").observe(viewLifecycleOwner,
            Observer<List<Restaurant>> {
                Timber.d("Nearest Restaurants: $it")
            })
    }
}
