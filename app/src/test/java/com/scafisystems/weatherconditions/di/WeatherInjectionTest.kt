package com.scafisystems.weatherconditions.di

import com.scafisystems.weatherconditions.model.entity.ApiResponse
import com.scafisystems.weatherconditions.model.repository.WeatherAPI
import com.scafisystems.weatherconditions.util.Constants.Companion.API_KEY
import com.scafisystems.weatherconditions.util.Constants.Companion.UNIT_METRIC
import com.scafisystems.weatherconditions.viewmodel.SelectCityViewModel
import com.scafisystems.weatherconditions.viewmodel.WeatherListViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito.*
import retrofit2.Response
import java.util.*

/**
 * Copyright (c) Created by André Scafi on 31/3/2021.
 */
class WeatherInjectionTest : KoinTest {


    val selectCityModel by inject<SelectCityViewModel>()
    val weatherListModel by inject<WeatherListViewModel>()
    val weatherAPI by inject<WeatherAPI>()


    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun should_work_SelectCityViewModel_when_injected() {
        val isCityNameRight = selectCityModel.validateNameCity()

        assertFalse(isCityNameRight)
        assertEquals(null, selectCityModel.cityName.value)
    }

    @Test
    fun should_work_WeatherListViewModel_when_injected() {
        val list = weatherListModel.getForecastList()

        assertEquals(0, list.size)
    }

    @Test
    fun should_work_WeatherAPI_when_injected() {
        val spy = spy(weatherAPI)
        val response = mock(Response::class.java) as Response<ApiResponse>

        runBlocking {
            `when`(
                spy.getWeather(
                    "London", API_KEY, UNIT_METRIC, Locale.getDefault().language
                )
            ).thenReturn(response)

            spy.getWeather(
                "London", API_KEY, UNIT_METRIC, Locale.getDefault().language
            )


            verify(spy, times(1)).getWeather(
                "London", API_KEY, UNIT_METRIC, Locale.getDefault().language
            )
        }


    }
}