package com.softbankrobotics.mvvm.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.softbankrobotics.mvvm.R
import com.squareup.picasso.Picasso

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_app_logo)
        .error(R.drawable.thumb)
        .into(view)
}