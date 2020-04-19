package com.fastnews.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.fastnews.mechanism.Retryable
import com.fastnews.service.api.RedditService
import com.fastnews.service.model.PostData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RedditPostsDataSource(
    private val service: RedditService,
    private val scope: CoroutineScope
) : PageKeyedDataSource<String, PostData>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, PostData>
    ) {
        val retryableCall = object : Retryable {
            override fun retry() {
                scope.launch {
                    try {
                        val response = service.getTop(limit = params.requestedLoadSize).await()
                        val data = response.body()?.data
                        val result = data?.children?.map { it.data } ?: mutableListOf()
                        callback.onResult(result, data?.before, data?.after)
                    } catch (t: Throwable) {
                        Log.e("RedditPostsDataSource", "Failed to fetch data!")
                        delay(2000)
                        loadInitial(params, callback)
                    }
                }
            }
        }
        retryableCall.start()
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, PostData>
    ) {
        val retryableCall = object : Retryable {
            override fun retry() {
                scope.launch {
                    try {
                        val response = service.getTopBefore(
                            before = params.key,
                            limit = params.requestedLoadSize
                        ).await()
                        val data = response.body()?.data
                        val result = data?.children?.map { it.data } ?: mutableListOf()
                        callback.onResult(result, data?.before)
                    } catch (t: Throwable) {
                        Log.e("RedditPostsDataSource", "Failed to fetch data!")
                        delay(2000)
                        loadBefore(params, callback)
                    }
                }
            }
        }
        retryableCall.start()
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PostData>) {
        val retryableCall = object : Retryable {
            override fun retry() {
                scope.launch {
                    try {
                        val response = service.getTopAfter(
                            after = params.key,
                            limit = params.requestedLoadSize
                        ).await()
                        val data = response.body()?.data
                        val result = data?.children?.map { it.data } ?: mutableListOf()
                        callback.onResult(result, data?.after)
                    } catch (t: Throwable) {
                        Log.e("RedditPostsDataSource", "Failed to fetch data!")
                        delay(2000)
                        loadAfter(params, callback)
                    }
                }
            }
        }
        retryableCall.start()
    }
}