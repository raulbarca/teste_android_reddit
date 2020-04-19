package com.fastnews.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fastnews.repository.PostRepositoryRobot.act
import com.fastnews.repository.PostRepositoryRobot.arrange
import com.fastnews.repository.PostRepositoryRobot.assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PostRepositoryTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when getPostsDataSourceFactory is called should return correct factory instance`() {
        arrange {
            mockService()
        }
        act {
            getPostsDataSourceFactory()
        }
        assert {
            isRedditPostsDataSource()
        }
    }
}