package com.fastnews.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fastnews.repository.RedditPostsDataSourceRobot.act
import com.fastnews.repository.RedditPostsDataSourceRobot.arrange
import com.fastnews.repository.RedditPostsDataSourceRobot.assert
import com.fastnews.utils.mockLog
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RedditPostsDataSourceTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockLog()
    }

    @Test
    fun `when error caught on loadInitial should not trigger callback`() {
        arrange {
            failingDataSource()
        }
        act {
            loadInitial()
        }
        assert {
            initialCallbackIsNotTriggered()
        }
    }

    @Test
    fun `when success response on loadInitial should trigger callback`() {
        arrange {
            successDataSource()
        }
        act {
            loadInitial()
        }
        assert {
            initialCallbackIsTriggered()
        }
    }

    @Test
    fun `when error caught on loadBefore should not trigger callback`() {
        arrange {
            failingDataSource()
        }
        act {
            loadBefore()
        }
        assert {
            beforeCallbackIsNotTriggered()
        }
    }

    @Test
    fun `when success response on loadBefore should trigger callback`() {
        arrange {
            successDataSource()
        }
        act {
            loadBefore()
        }
        assert {
            beforeCallbackIsTriggered()
        }
    }

    @Test
    fun `when error caught on loadAfter should not trigger callback`() {
        arrange {
            failingDataSource()
        }
        act {
            loadAfter()
        }
        assert {
            afterCallbackIsNotTriggered()
        }
    }

    @Test
    fun `when success response on loadAfter should trigger callback`() {
        arrange {
            successDataSource()
        }
        act {
            loadAfter()
        }
        assert {
            afterCallbackIsTriggered()
        }
    }
}