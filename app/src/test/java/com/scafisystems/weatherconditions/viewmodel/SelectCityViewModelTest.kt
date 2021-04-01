package com.scafisystems.weatherconditions.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Copyright (c) Created by André Scafi on 31/3/2021.
 */
class SelectCityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var exception = ExpectedException.none()

    lateinit var viewModel: SelectCityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SelectCityViewModel()
    }

    @Test
    fun should_cityName_receive_value_when_is_attributed() {
        viewModel.cityName.value = "London"
        assertEquals("London", viewModel.cityName.value)

    }

    @Test
    fun should_return_true_when_the_city_name_is_not_empty() {
        viewModel.cityName.value = "London"
        val validateNameCity = viewModel.validateNameCity()
        assertTrue(validateNameCity)

    }

    @Test
    fun should_return_false_when_the_city_name_is_empty() {
        viewModel.cityName.value = ""
        val validateNameCity = viewModel.validateNameCity()
        assertFalse(validateNameCity)

    }

    @Test
    fun should_call_validateNameCity_when_call_onClickToListWeather() {
        viewModel.cityName.value = ""

        val spy = spy(viewModel)

        exception.expect(NullPointerException::class.java)

        spy.onClickToListWeather(mock(View::class.java))

        verify(spy, times(1)).validateNameCity()

    }

    @Test
    fun should_call_Toast_when_name_is_empty() {
        viewModel.cityName.value = ""

        val spy = spy(viewModel)

        exception.expect(RuntimeException::class.java)
       
        `when`(spy.validateNameCity()).thenThrow(RuntimeException("Toast displayed"))

        spy.onClickToListWeather(mock(View::class.java))

        verify(spy, times(1)).validateNameCity()

    }

    @Test
    fun should_change_fragment_when_name_is_not_empty() {
        val view = mock(View::class.java)
        viewModel.cityName.value = ""

        val spy = spy(viewModel)

        exception.expect(RuntimeException::class.java)
        `when`(spy.goToListFragment(view)).thenThrow(RuntimeException("goToListFragment called"))

        spy.onClickToListWeather(view)

        verify(spy, times(1)).goToListFragment(view)

    }
}