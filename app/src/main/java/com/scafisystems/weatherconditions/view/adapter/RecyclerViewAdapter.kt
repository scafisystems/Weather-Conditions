package com.scafisystems.weatherconditions.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.scafisystems.weatherconditions.R
import com.scafisystems.weatherconditions.databinding.WeatherItemBinding
import com.scafisystems.weatherconditions.model.entity.ApiResponse.Forecast
import com.scafisystems.weatherconditions.util.formatTo
import com.scafisystems.weatherconditions.util.toCelsus
import com.scafisystems.weatherconditions.util.toDate

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
class RecyclerViewAdapter(private val forecastList:List<Forecast>, private val clickListener:(Forecast)->Unit) : RecyclerView.Adapter<WeatherViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =  DataBindingUtil.inflate<WeatherItemBinding>(inflater, R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holderWeather: WeatherViewHolder, position: Int) {
          holderWeather.bind(forecastList[position], clickListener)
    }

}

class WeatherViewHolder(val view : WeatherItemBinding) : RecyclerView.ViewHolder(view.root){

    fun bind(forecast: Forecast, clickListener:(Forecast)->Unit){
        view.text =  "${forecast.dtTxt.toDate().formatTo("dd MMM yy")}\n" +
                        "${forecast.weather.first().description.capitalize()}\n" +
                        "${forecast.weather.first().main}\n" +
                        "${forecast.main.temp.toCelsus()}"

        view.linearLayout.setOnClickListener {
            clickListener(forecast)
        }

    }
}