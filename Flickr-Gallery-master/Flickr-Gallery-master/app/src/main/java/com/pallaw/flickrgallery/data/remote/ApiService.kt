package com.pallaw.flickrgallery.data.remote

import com.pallaw.flickrgallery.data.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
interface ApiService {

    @Headers("content-type: application/json")
    @GET("/services/rest/?method=flickr.photos.search")
    fun searchTag(@Query("tags") tag: String, @Query("page") page: Int): Single<SearchResponse>

}