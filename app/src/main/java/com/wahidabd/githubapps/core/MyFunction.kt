package com.wahidabd.githubapps.core

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wahidabd.githubapps.R

fun ImageView.setImage(url: String) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_baseline_person_24)
        .into(this)