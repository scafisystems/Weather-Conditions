package com.scafisystems.weatherconditions.model.repository

import com.google.gson.GsonBuilder
import com.scafisystems.weatherconditions.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
class WeatherAPIService {
    companion object {

        private val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

    }
}