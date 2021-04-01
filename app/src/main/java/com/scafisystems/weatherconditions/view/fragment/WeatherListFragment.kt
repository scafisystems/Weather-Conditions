package com.scafisystems.weatherconditions.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.weatherconditions.R
import com.scafisystems.weatherconditions.databinding.FragmentWeatherListBinding
import com.scafisystems.weatherconditions.model.entity.ApiResponse.Forecast
import com.scafisystems.weatherconditions.view.adapter.RecyclerViewAdapter
import com.scafisystems.weatherconditions.viewmodel.WeatherListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
class WeatherListFragment : Fragment() {

    private lateinit var binding: FragmentWeatherListBinding
    val viewModel: WeatherListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        arguments?.let { bundle ->
            viewModel.cityName.value = WeatherListFragmentArgs.fromBundle(bundle).cityName
            (activity as AppCompatActivity).supportActionBar?.title = viewModel.cityName.value
        }

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_weather_list, container, false
        )

        binding.loadingView.visibility = View.VISIBLE
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.myRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.myRecyclerView.setHasFixedSize(true)

        viewModel.responseLiveData.observe(viewLifecycleOwner, Observer {
            binding.myRecyclerView.adapter = RecyclerViewAdapter(viewModel.getForecastList())
            { selectedForecastItem: Forecast -> listItemClicked(selectedForecastItem) }
            binding.loadingView.visibility = View.GONE
        })

        viewModel.sucecess.observe(viewLifecycleOwner, Observer {
            if(viewModel.sucecess.value == false){
                binding.listError.visibility = View.VISIBLE
                binding.loadingView.visibility = View.GONE
            }
        })

        return binding.root
    }

    private fun listItemClicked(forecast: Forecast) {
        viewModel.goToWeatherDetails(binding.myRecyclerView, forecast)

    }


}