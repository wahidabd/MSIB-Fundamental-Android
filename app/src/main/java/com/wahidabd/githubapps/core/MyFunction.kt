package com.wahidabd.githubapps.core

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.wahidabd.githubapps.R

fun ImageView.setImage(url: String) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_avater_placeholder)
        .into(this)

fun TextView.mySetText(data: String?) {
    if (data == null || data == "") this.visibility = View.GONE
    else this.text = data
}
