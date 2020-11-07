package com.chentir.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navDeepLink

class RestaurantDetailFragment : Fragment() {
    companion object {
        fun newInstance() = RestaurantDetailFragment()
    }

    private lateinit var viewModel: RestaurantDetailViewModel
    private lateinit var restaurantId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RestaurantDetailViewModel::class.java)
        findNavController().getBackStackEntry(R.id.restaurantDetailFragment).arguments?.let {
            restaurantId = it["restaurant_id"] as String
        }
    }
}
