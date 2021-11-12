package com.pallaw.flickrgallery.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pallaw.flickrgallery.data.model.Photo
import com.pallaw.flickrgallery.data.repository.PhotoPagedListRepository
import com.pallaw.flickrphotos.util.NetworkState
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Pallaw Pathak on 21/04/20. - https://www.linkedin.com/in/pallaw-pathak-a6a324a1/
 */
class MainActivityViewModel(
    photoRepository: PhotoPagedListRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val searchTag = MutableLiveData<String>()

    val searchPagedList: LiveData<PagedList<Photo>> by lazy {
        photoRepository.fetchLiveSearchPhotoPagedList(searchTag, compositeDisposable)
    }

    val searchPhotosNetworkState: LiveData<NetworkState>? by lazy {
        photoRepository.getNetworkState()
    }

    fun isSearchListEmpty(): Boolean {
        return searchPagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun searchTag(tag: String) {
        searchTag.postValue(tag)
    }

}