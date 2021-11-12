package com.pallaw.flickrgallery.data.model


import com.google.gson.annotations.SerializedName

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
data class SearchResponse(
    @SerializedName("photos")
    var photos: Photos = Photos(),
    @SerializedName("stat")
    var stat: String = ""
) {
    data class Photos(
        @SerializedName("page")
        var page: Int = 0,
        @SerializedName("pages")
        var pages: Int = 0,
        @SerializedName("perpage")
        var perpage: Int = 0,
        @SerializedName("photo")
        var photo: List<Photo> = listOf(),
        @SerializedName("total")
        var total: String = ""
    )
}