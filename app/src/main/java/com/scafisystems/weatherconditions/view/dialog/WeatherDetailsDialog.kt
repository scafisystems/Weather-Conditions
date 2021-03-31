package com.scafisystems.weatherconditions.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.scafisystems.weatherconditions.R
import com.scafisystems.weatherconditions.databinding.DialogWeatherDetailsBinding
import com.scafisystems.weatherconditions.util.formatTo
import com.scafisystems.weatherconditions.util.toDate


/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
class WeatherDetailsDialog : DialogFragment() {

    private lateinit var binding: DialogWeatherDetailsBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { it ->

            val builder = AlertDialog.Builder(it)
            binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.dialog_weather_details,null,false)
            builder.setView(binding.root)

            binding.dialog = this

            arguments?.let {bundle ->
                binding.forecast = WeatherDetailsDialogArgs.fromBundle(bundle).forecast
            }


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun convertDate(dateFormat: String): String{
        return dateFormat.toDate().formatTo("dd MMM yy HH:mm")
    }

}