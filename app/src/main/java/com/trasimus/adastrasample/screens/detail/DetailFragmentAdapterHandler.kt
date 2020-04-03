package com.trasimus.adastrasample.screens.detail

import android.widget.ImageView
import android.widget.ProgressBar

interface DetailFragmentAdapterHandler {
    fun inflateImage(url: String, imageView: ImageView, progressBar: ProgressBar)
}