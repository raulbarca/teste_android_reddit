package com.fastnews.repository

import androidx.paging.DataSource
import com.fastnews.service.api.RedditService
import com.fastnews.service.model.PostData
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object PostRepositoryRobot {
    private lateinit var subject: PostRepository
    private lateinit var service: RedditService
    private lateinit var result: DataSource.Factory<String, PostData>

    class PostRepositoryRobotArrange {
        fun mockService() {
            service = mockk()
            subject = PostRepositoryImpl(service)
        }
    }

    class PostRepositoryRobotAct {
        fun getPostsDataSourceFactory() {
            result = subject.getPostsDataSourceFactory(CoroutineScope(Dispatchers.Unconfined))
        }
    }

    class PostRepositoryRobotAssert {
        fun isRedditPostsDataSource() {
            assert(result.create() is RedditPostsDataSource)
        }
    }

    fun arrange(func: PostRepositoryRobotArrange.() -> Unit) =
        PostRepositoryRobotArrange().apply(func)

    fun act(func: PostRepositoryRobotAct.() -> Unit) =
        PostRepositoryRobotAct().apply(func)

    fun assert(func: PostRepositoryRobotAssert.() -> Unit) =
        PostRepositoryRobotAssert().apply(func)
}