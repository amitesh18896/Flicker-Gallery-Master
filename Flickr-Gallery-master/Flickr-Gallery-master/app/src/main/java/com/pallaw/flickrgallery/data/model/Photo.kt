package com.pallaw.flickrgallery.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
data class Photo(
    @SerializedName("farm")
    var farm: String = "",
    @SerializedName("server")
    var server: String = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("secret")
    var secret: String = ""
)

fun Photo.getPhotoUrl(): String {
    return String.format(
        "https://farm%s.staticflickr.com/%s/%s_%s.jpg",
        this.farm,
        this.server,
        this.id,
        this.secret
    )
}