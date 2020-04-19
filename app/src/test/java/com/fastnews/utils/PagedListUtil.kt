package com.fastnews.utils

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.fastnews.repository.RedditPostsDataSource
import com.fastnews.service.model.PostData
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun createPostMockDataSourceFactory(
    itemList: List<PostData>
): DataSource.Factory<String, PostData> {
    val service = mockServiceSuccess(itemList)
    val scope = CoroutineScope(Dispatchers.Unconfined)
    val dataSource = RedditPostsDataSource(service, scope)
    return object : DataSource.Factory<String, PostData>() {
        override fun create(): DataSource<String, PostData> = dataSource
    }
}

fun createPostMockDataSourceFactoryWithFailure(): DataSource.Factory<String, PostData> {
    val service = mockServiceFailure()
    val scope = CoroutineScope(Dispatchers.Unconfined)
    val dataSource = RedditPostsDataSource(service, scope)
    return object : DataSource.Factory<String, PostData>() {
        override fun create(): DataSource<String, PostData> = dataSource
    }
}

fun mockInitialCallback(
    resultList: List<PostData>,
    resultTotalCount: Int,
    resultPosition: Int,
    resultBefore: String?,
    resultAfter: String?
): PageKeyedDataSource.LoadInitialCallback<String, PostData> {
    val callback = mockk<PageKeyedDataSource.LoadInitialCallback<String, PostData>>()
    every {
        callback.onResult(
            resultList,
            resultTotalCount,
            resultPosition,
            resultBefore,
            resultAfter
        )
    } just Runs
    every {
        callback.onResult(
            resultList,
            resultBefore,
            resultAfter
        )
    } just Runs
    return callback
}

fun mockCallback(
    resultList: List<PostData>,
    resultAdjacent: String?
): PageKeyedDataSource.LoadCallback<String, PostData> {
    val callback = mockk<PageKeyedDataSource.LoadCallback<String, PostData>>()
    every {
        callback.onResult(
            resultList,
            resultAdjacent
        )
    } just Runs
    return callback
}
