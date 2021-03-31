package com.scafisystems.weatherconditions.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */

data class ApiResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: String,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<Forecast>,
    @SerializedName("message")
    val message: String
) : Serializable {

    data class City(
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("country")
        val country: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("population")
        val population: String,
        @SerializedName("sunrise")
        val sunrise: String,
        @SerializedName("sunset")
        val sunset: String,
        @SerializedName("timezone")
        val timezone: String
    ) : Serializable{
        data class Coord(
            @SerializedName("lat")
            val lat: String,
            @SerializedName("lon")
            val lon: String
        )
    }

    data class Forecast(
        @SerializedName("clouds")
        val clouds: Clouds,
        @SerializedName("dt")
        val dt: String,
        @SerializedName("dt_txt")
        val dtTxt: String,
        @SerializedName("main")
        val main: Main,
        @SerializedName("pop")
        val pop: String,
        @SerializedName("rain")
        val rain: Rain,
        @SerializedName("sys")
        val sys: Sys,
        @SerializedName("visibility")
        val visibility: String,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("wind")
        val wind: Wind
    ): Serializable{
        data class Clouds(
            @SerializedName("all")
            val all: String
        )

        data class Main(
            @SerializedName("feels_like")
            val feelsLike: String,
            @SerializedName("grnd_level")
            val grndLevel: String,
            @SerializedName("humidity")
            val humidity: String,
            @SerializedName("pressure")
            val pressure: String,
            @SerializedName("sea_level")
            val seaLevel: String,
            @SerializedName("temp")
            val temp: String,
            @SerializedName("temp_kf")
            val tempKf: String,
            @SerializedName("temp_max")
            val tempMax: String,
            @SerializedName("temp_min")
            val tempMin: String
        )

        data class Rain(
            @SerializedName("3h")
            val h: String
        )

        data class Sys(
            @SerializedName("pod")
            val pod: String
        )

        data class Weather(
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("main")
            val main: String
        )

        data class Wind(
            @SerializedName("deg")
            val deg: String,
            @SerializedName("speed")
            val speed: String
        )
    }
}