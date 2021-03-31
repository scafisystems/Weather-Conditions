package com.scafisystems.weatherconditions.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.scafisystems.weatherconditions.R

/**
 * Copyright (c) Created by André Scafi on 27/3/2021.
 */

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, view: View) {
    val uriCompleted = "http://openweathermap.org/img/wn/${uri}@2x.png"
    val options = RequestOptions()
        .placeholder(getProgressDrawable(context))
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uriCompleted)
        .into(this)


}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImage(url, view)
}

