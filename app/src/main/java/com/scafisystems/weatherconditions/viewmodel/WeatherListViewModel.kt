package com.scafisystems.weatherconditions.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.navigation.Navigation
import com.scafisystems.weatherconditions.model.entity.ApiResponse
import com.scafisystems.weatherconditions.model.entity.ApiResponse.Forecast
import com.scafisystems.weatherconditions.model.repository.WeatherAPI
import com.scafisystems.weatherconditions.util.Constants.Companion.API_KEY
import com.scafisystems.weatherconditions.util.Constants.Companion.UNIT_METRIC
import com.scafisystems.weatherconditions.view.fragment.WeatherListFragmentDirections
import retrofit2.Response
import java.util.*

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */

class WeatherListViewModel(private val retService: WeatherAPI) : ViewModel() {

    val cityName = MutableLiveData<String>()
    val responseLiveData: LiveData<Response<ApiResponse>> = callRetrofit()


    fun callRetrofit(): LiveData<Response<ApiResponse>> {
        return liveData {
            val response = retService.getWeather(
                cityName.value!!,
                API_KEY,
                UNIT_METRIC,
                Locale.getDefault().language
            )
            emit(response)
        }
    }

    fun getForecastList(): List<Forecast> {
        val list = mutableListOf<Forecast>()
        responseLiveData.value?.body()?.let {
            list.addAll(it.list)
        }
        return list
    }

    fun goToWeatherDetails(view: View, forecast: Forecast) {
        val action =
            WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailsDialog(forecast)
        Navigation.findNavController(view).navigate(action)
    }

}