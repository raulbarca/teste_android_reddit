package com.fastnews.viewmodel

import com.fastnews.repository.PostRepository
import com.fastnews.service.model.PostData
import com.fastnews.utils.PostUtil
import com.fastnews.utils.createPostMockDataSourceFactory
import com.fastnews.utils.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk

object PostViewModelRobot {
    private lateinit var repository: PostRepository
    private lateinit var postViewModel: PostViewModel

    class PostViewModelRobotArrange {
        private fun mockRepository(itemList: List<PostData>): PostRepository {
            return mockk {
                every {
                    getPostsDataSourceFactory(any())
                } returns createPostMockDataSourceFactory(itemList)
            }
        }

        fun repositorySuccessResponse() {
            repository = mockRepository(PostUtil.oneItemPostList)
        }
    }

    class PostViewModelRobotAct {
        fun initViewModel() {
            postViewModel = PostViewModel(repository)
        }
    }

    class PostViewModelRobotAssert {
        fun liveDataHasExpectedSize() {
            val value = postViewModel.posts.getOrAwaitValue()
            assert(value.size == 1)
        }
    }

    fun arrange(func: PostViewModelRobotArrange.() -> Unit) =
        PostViewModelRobotArrange().apply(func)

    fun act(func: PostViewModelRobotAct.() -> Unit) =
        PostViewModelRobotAct().apply(func)

    fun assert(func: PostViewModelRobotAssert.() -> Unit) =
        PostViewModelRobotAssert().apply(func)
}