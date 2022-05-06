package com.intermedia.challenge.utils.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.intermedia.challenge.data.models.Thumbnail
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageThumbnail")
fun setImage(view: ImageView, thumbnail: Thumbnail) {
    val url = "${thumbnail.path}.${thumbnail.extension}".replace("http", "https")
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("android:isVisible")
fun View.setIsVisible(visible: Boolean?) {
    visibility = if (visible != null && visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:date")
fun setDate(view: TextView, date: String?) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    if(!date.isNullOrEmpty()) {
        val dateValue = dateFormat.parse(date)
        val outputDate: DateFormat = DateFormat.getDateInstance(DateFormat.LONG)
        view.text = outputDate.format(dateValue)
    }
}