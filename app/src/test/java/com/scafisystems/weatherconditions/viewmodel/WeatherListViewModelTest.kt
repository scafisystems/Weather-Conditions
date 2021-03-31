package com.scafisystems.weatherconditions.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.scafisystems.weatherconditions.model.entity.ApiResponse.Forecast
import com.scafisystems.weatherconditions.model.repository.WeatherAPI
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Copyright (c) Created by André Scafi on 31/3/2021.
 */
class WeatherListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var exception = ExpectedException.none()

    @Mock
    lateinit var viewModel: WeatherListViewModel

    @Mock
    lateinit var service: WeatherAPI


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = WeatherListViewModel(service)
    }

    @Test
    fun should_cityName_receive_value_when_is_attributed() {
        viewModel.cityName.value = "London"
        assertEquals("London", viewModel.cityName.value)

    }

    @Test
    fun should_call_Retrofit_when_call_getForecastList() {
        viewModel.cityName.value = ""

        val spy = Mockito.spy(viewModel)

        exception.expect(RuntimeException::class.java)
        exception.expectMessage("call Retrofit")
        `when`(spy.getForecastList()).thenThrow(RuntimeException("call Retrofit"))

        spy.getForecastList()

        verify(spy, times(1)).callRetrofit()
    }

    @Test
    fun should_return_list_of_forecast_when_call_getForecastList() {
        viewModel = mock(WeatherListViewModel::class.java)

        `when`(viewModel.getForecastList()).thenReturn(fakeList())

        val list = viewModel.getForecastList()
        assertEquals(10, list.size)
        assertEquals("TEST1", list[0].weather[0].description)
        assertEquals("TEST10", list[9].weather[0].description)

    }

    @Test
    fun should_go_details_dialog_when_list_Item_Clicked() {
        val view = mock(View::class.java)
        val forecast = mock(Forecast::class.java)

        val spy = spy(viewModel)

        exception.expect(RuntimeException::class.java)
        `when`(
            spy.goToWeatherDetails(
                view,
                forecast
            )
        ).thenThrow(RuntimeException("goToWeatherDetails called"))

        spy.goToWeatherDetails(view, forecast)

        verify(spy, times(1)).goToWeatherDetails(view, forecast)

    }

    fun fakeList(): List<Forecast> {
        return listOf(
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST1", "04d", "01", "Main1")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST2", "04d", "01", "Main2")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST3", "04d", "01", "Main3")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST4", "04d", "01", "Main4")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST5", "04d", "01", "Main5")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST6", "04d", "01", "Main6")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST7", "04d", "01", "Main7")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST8", "04d", "01", "Main8")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST9", "04d", "01", "Main9")),
                Forecast.Wind("", "")
            ),
            Forecast(
                Forecast.Clouds(""),
                "",
                "",
                Forecast.Main("", "", "", "", "", "", "", "", ""),
                "",
                Forecast.Rain(""),
                Forecast.Sys(""),
                "",
                listOf(Forecast.Weather("TEST10", "04d", "01", "Main10")),
                Forecast.Wind("", "")
            )
        )
    }
}