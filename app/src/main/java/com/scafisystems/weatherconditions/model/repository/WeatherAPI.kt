package com.scafisystems.weatherconditions.model.repository

import com.scafisystems.weatherconditions.model.entity.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
interface WeatherAPI {

    @GET("data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") unit: String,
        @Query("lang") lang: String
    ): Response<ApiResponse>


}