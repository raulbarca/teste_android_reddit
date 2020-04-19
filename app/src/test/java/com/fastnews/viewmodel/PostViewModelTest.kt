package com.fastnews.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fastnews.utils.mockLog
import com.fastnews.viewmodel.PostViewModelRobot.act
import com.fastnews.viewmodel.PostViewModelRobot.arrange
import com.fastnews.viewmodel.PostViewModelRobot.assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PostViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockLog()
    }

    @Test
    fun `when view model created should init live data`() {
        arrange {
            repositorySuccessResponse()
        }
        act {
            initViewModel()
        }
        assert {
            liveDataHasExpectedSize()
        }
    }
}