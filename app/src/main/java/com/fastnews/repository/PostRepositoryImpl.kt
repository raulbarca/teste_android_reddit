package com.fastnews.repository

import androidx.paging.DataSource
import com.fastnews.service.api.RedditService
import com.fastnews.service.model.PostData
import kotlinx.coroutines.CoroutineScope

class PostRepositoryImpl(
    private val service: RedditService
) : PostRepository {
    override fun getPostsDataSourceFactory(scope: CoroutineScope) =
        object : DataSource.Factory<String, PostData>() {
            override fun create(): DataSource<String, PostData> {
                return RedditPostsDataSource(service, scope)
            }
        }
}