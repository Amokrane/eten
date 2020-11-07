package com.chentir.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.chentir.domain.entities.RestaurantDetail
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class RestaurantDetailFragment : Fragment() {
    companion object {
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

        restaurantId?.let {
            viewModel.fetchRestaurantDetail(it)
                .observe(viewLifecycleOwner, Observer<RestaurantDetail> { restaurantDetail ->
                    Timber.d("restaurantDetail $restaurantDetail")
                })
        }
    }
}
