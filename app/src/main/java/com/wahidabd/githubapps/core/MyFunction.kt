package com.wahidabd.githubapps.core

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.wahidabd.githubapps.R
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.setImage(url: String) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_avater_placeholder)
        .into(this)

fun TextView.mySetText(data: String?) {
    if (data == null || data == "") this.visibility = View.GONE
    else this.text = data
}

fun currentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}
