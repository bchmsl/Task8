package com.bchmsl.task8.common.extensions

import android.widget.ImageView
import com.bchmsl.task8.R
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String){
    Glide.with(this).load(url).placeholder(R.drawable.img_placeholder).into(this)
}