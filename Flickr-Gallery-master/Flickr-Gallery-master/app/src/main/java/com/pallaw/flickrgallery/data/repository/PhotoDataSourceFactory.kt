package com.pallaw.flickrgallery.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pallaw.flickrgallery.data.model.Photo
import com.pallaw.flickrgallery.data.remote.ApiService
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
class PhotoDataSourceFactory(
    private val tag: String,
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Photo>() {

    val photosLiveDataSource = MutableLiveData<PhotoDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val photoDataSource =
            PhotoDataSource(
                tag,
                apiService,
                compositeDisposable
            )

        photosLiveDataSource.postValue(photoDataSource)
        return photoDataSource
    }
}