package com.scafisystems.weatherconditions.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.scafisystems.weatherconditions.R
import com.scafisystems.weatherconditions.databinding.FragmentSelectCityBinding
import com.scafisystems.weatherconditions.viewmodel.SelectCityViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */
class SelectCityFragment : Fragment() {

    private lateinit var binding: FragmentSelectCityBinding
    private val viewModel: SelectCityViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_city, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}