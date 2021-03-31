package com.scafisystems.weatherconditions.di


import com.scafisystems.weatherconditions.model.repository.WeatherAPI
import com.scafisystems.weatherconditions.model.repository.WeatherAPIService
import com.scafisystems.weatherconditions.viewmodel.WeatherListViewModel
import com.scafisystems.weatherconditions.viewmodel.SelectCityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
val appModule = module {

    // single instance of WeatherAPIService
    single<WeatherAPI> { WeatherAPIService.getRetrofitInstance().create(WeatherAPI::class.java) }

    // ViewModel selectCity
    viewModel { SelectCityViewModel() }

    // ViewModel WeatherList
    viewModel { WeatherListViewModel(get()) }

}