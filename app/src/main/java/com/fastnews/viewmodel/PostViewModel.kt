package com.fastnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fastnews.repository.PostRepository
import com.fastnews.service.model.PostData

class PostViewModel(
    repository: PostRepository
) : ViewModel() {
    val posts: LiveData<PagedList<PostData>>

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        val dataSourceFactory = repository.getPostsDataSourceFactory(viewModelScope)
        posts = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}