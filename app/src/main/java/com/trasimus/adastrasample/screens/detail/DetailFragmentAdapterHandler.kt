package com.trasimus.adastrasample.screens.detail

import android.widget.ImageView

interface DetailFragmentAdapterHandler {
    fun inflateImage(url: String, imageView: ImageView)
}