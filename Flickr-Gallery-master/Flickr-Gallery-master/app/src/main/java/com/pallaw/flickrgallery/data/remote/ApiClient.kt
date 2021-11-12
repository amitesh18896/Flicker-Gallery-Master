package com.pallaw.flickrgallery.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
const val API_KEY = "3e7cc266ae2b0e0d78e279ce8e361736"
const val BASE_URL = "https://api.flickr.com"

const val FIRST_PAGE = 1
const val ITEM_PER_PAGE = 15
const val RESPONSE_FORMAT = "json"
const val JSON_CALLBACK = "1"

object ApiClient {

    fun getClient(): ApiService {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(getCommonRequestInterceptor())
            .addInterceptor(getLoggingInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    private fun getCommonRequestInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .addQueryParameter("per_page", "$ITEM_PER_PAGE")
                    .addQueryParameter("format", RESPONSE_FORMAT)
                    .addQueryParameter("nojsoncallback", "$JSON_CALLBACK")
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return chain.proceed(request)
            }
        }
    }

    private fun getLoggingInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                Timber.tag("Okhttp_request").d(request.toString())

                val response = chain.proceed(request)
                val rawJson = response.body!!.string()

                Timber.tag("Okhttp_response").d(rawJson)

                return response.newBuilder()
                    .body(rawJson.toResponseBody(response.body!!.contentType())).build()
            }
        }
    }
}