package com.fastnews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fastnews.repository.PostRepository
import com.fastnews.service.model.PostData

class PostViewModel(application: Application) : AndroidViewModel(application) {
    var posts: LiveData<PagedList<PostData>>

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        val dataSourceFactory = PostRepository.getPostsDataSourceFactory(viewModelScope)
        posts = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}