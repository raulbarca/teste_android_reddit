package com.fastnews.repository

import androidx.paging.PageKeyedDataSource
import com.fastnews.service.model.PostData
import com.fastnews.utils.*
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

typealias ParamInitial = PageKeyedDataSource.LoadInitialParams<String>
typealias CallbackInitial = PageKeyedDataSource.LoadInitialCallback<String, PostData>
typealias Param = PageKeyedDataSource.LoadParams<String>
typealias Callback = PageKeyedDataSource.LoadCallback<String, PostData>

object RedditPostsDataSourceRobot {
    private lateinit var subject: RedditPostsDataSource
    private lateinit var resultList: List<PostData>
    private lateinit var resultAfter: String
    private lateinit var resultBefore: String
    private var initialParameters: Pair<ParamInitial, CallbackInitial>? = null
    private var parameters: Pair<Param, Callback>? = null
    private var resultTotalCount: Int = 0
    private var resultPosition: Int = 0

    class RedditPostsDataSourceRobotArrange {
        fun failingDataSource() {
            val service = mockServiceFailure()
            val scope = CoroutineScope(Dispatchers.Unconfined)
            subject = RedditPostsDataSource(service, scope)
            resultList = mutableListOf()
            resultBefore = "before"
            resultAfter = "after"
        }

        fun successDataSource() {
            val service = mockServiceSuccess(PostUtil.manyItemsPostList)
            val scope = CoroutineScope(Dispatchers.Unconfined)
            subject = RedditPostsDataSource(service, scope)
            resultList = PostUtil.manyItemsPostList
            resultBefore = "before"
            resultAfter = "after"
        }
    }

    class RedditPostsDataSourceRobotAct {
        fun loadInitial() {
            val callback = mockInitialCallback(
                resultList,
                resultTotalCount,
                resultPosition,
                resultBefore,
                resultAfter
            )
            initialParameters = Pair(ParamInitial(15, false), callback)
            subject.loadInitial(initialParameters!!.first, initialParameters!!.second)
        }

        fun loadBefore() {
            val callback = mockCallback(
                resultList,
                resultBefore
            )
            parameters = Pair(Param(resultBefore, 15), callback)
            subject.loadBefore(parameters!!.first, parameters!!.second)
        }

        fun loadAfter() {
            val callback = mockCallback(
                resultList,
                resultAfter
            )
            parameters = Pair(Param(resultAfter, 15), callback)
            subject.loadAfter(parameters!!.first, parameters!!.second)
        }
    }

    class RedditPostsDataSourceRobotAssert {
        fun initialCallbackIsNotTriggered() {
            verify(inverse = true) {
                initialParameters!!.second.onResult(resultList, resultBefore, resultAfter)
            }
        }

        fun initialCallbackIsTriggered() {
            verify(exactly = 1) {
                initialParameters!!.second.onResult(resultList, resultBefore, resultAfter)
            }
        }

        fun beforeCallbackIsNotTriggered() {
            verify(inverse = true) {
                parameters!!.second.onResult(resultList, resultBefore)
            }
        }

        fun beforeCallbackIsTriggered() {
            verify(exactly = 1) {
                parameters!!.second.onResult(resultList, resultBefore)
            }
        }

        fun afterCallbackIsNotTriggered() {
            verify(inverse = true) {
                parameters!!.second.onResult(resultList, resultAfter)
            }
        }

        fun afterCallbackIsTriggered() {
            verify(exactly = 1) {
                parameters!!.second.onResult(resultList, resultAfter)
            }
        }
    }

    fun arrange(func: RedditPostsDataSourceRobotArrange.() -> Unit) =
        RedditPostsDataSourceRobotArrange().apply(func)

    fun act(func: RedditPostsDataSourceRobotAct.() -> Unit) =
        RedditPostsDataSourceRobotAct().apply(func)

    fun assert(func: RedditPostsDataSourceRobotAssert.() -> Unit) =
        RedditPostsDataSourceRobotAssert().apply(func)
}