package com.fastnews.repository

import androidx.paging.DataSource
import com.fastnews.service.api.RedditAPI
import com.fastnews.service.model.PostData
import kotlinx.coroutines.CoroutineScope

object PostRepository : BaseRepository() {
    fun getPostsDataSourceFactory(scope: CoroutineScope) =
        object : DataSource.Factory<String, PostData>() {
            override fun create(): DataSource<String, PostData> {
                return RedditPostsDataSource(RedditAPI.redditService, scope)
            }
        }
}