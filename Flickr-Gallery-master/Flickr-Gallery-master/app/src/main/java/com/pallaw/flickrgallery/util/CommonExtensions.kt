package com.pallaw.flickrgallery.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pallaw.flickrgallery.R

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_loading)
        .into(this)
}