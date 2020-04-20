package com.fastnews.ui.timeline

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fastnews.rule.createRule
import com.fastnews.runner.RedditTestApp
import com.fastnews.ui.timeline.TimelineFragmentRobot.act
import com.fastnews.ui.timeline.TimelineFragmentRobot.arrange
import com.fastnews.ui.timeline.TimelineFragmentRobot.assert
import com.fastnews.viewmodel.PostViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = RedditTestApp::class)
class TimelineFragmentTest {
    private val fragmentViewModel: PostViewModel = mockk(relaxed = true)
    private val fragment = TimelineFragment()

    @get:Rule
    var fragmentRule = createRule(fragment, module {
        single(override = true) {
            fragmentViewModel
        }
    })

    @Before
    fun setUp() {
        TimelineFragmentRobot.setUp()
    }

    @After
    fun tearDown() {
        TimelineFragmentRobot.tearDown()
    }

    @Test
    fun `when no network connection should display no network view`() {
        arrange {
            noNetworkConnection()
        }
        act {
            startFragment()
        }
        assert {
            isNotNetworkViewDisplayed()
        }
    }

    @Test
    fun `when network connection is ok should display posts view`() {
        arrange {
            goodNetworkConnection()
        }
        act {
            startFragment()
        }
        assert {
            isNetworkViewDisplayed()
        }
    }
}