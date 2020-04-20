package com.fastnews.repository

import androidx.paging.DataSource
import com.fastnews.service.model.PostData
import kotlinx.coroutines.CoroutineScope

interface PostRepository {
    fun getPostsDataSourceFactory(scope: CoroutineScope): DataSource.Factory<String, PostData>
}