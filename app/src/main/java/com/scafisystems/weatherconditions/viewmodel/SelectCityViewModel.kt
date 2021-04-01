package com.scafisystems.weatherconditions.viewmodel

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import com.scafisystems.weatherconditions.R
import com.scafisystems.weatherconditions.util.isConnected
import com.scafisystems.weatherconditions.view.fragment.SelectCityFragmentDirections

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
class SelectCityViewModel : ViewModel() {

    val cityName = MutableLiveData<String>()

    fun onClickToListWeather(view: View) {
        if (isConnected(view.context)) {

            if (validateNameCity()) {
                goToListFragment(view)
            } else {
                Toast.makeText(view.context, R.string.insert_city_name, Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(view.context, R.string.connect_to_internet, Toast.LENGTH_LONG).show()
        }

    }

    fun validateNameCity(): Boolean {
        return !TextUtils.isEmpty(cityName.value) && !cityName.value.isNullOrEmpty()
    }

    fun goToListFragment(view: View) {
        val action =
            SelectCityFragmentDirections.actionSelectCityFragmentToWeatherListFragment(cityName.value!!)
        findNavController(view).navigate(action)
    }

}