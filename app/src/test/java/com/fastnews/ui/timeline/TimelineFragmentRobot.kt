package com.fastnews.ui.timeline

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.fastnews.R
import com.fastnews.mechanism.VerifyNetworkInfo
import com.fastnews.ui.FragmentActivityUtils
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll

object TimelineFragmentRobot {
    private lateinit var scenario: FragmentScenario<TimelineFragment>

    class TimelineFragmentRobotArrange {
        fun noNetworkConnection() {
            mockkObject(VerifyNetworkInfo)
            every { VerifyNetworkInfo.isConnected(any()) } returns false
        }

        fun goodNetworkConnection() {
            mockkObject(VerifyNetworkInfo)
            every { VerifyNetworkInfo.isConnected(any()) } returns true
        }
    }

    class TimelineFragmentRobotAct {
        fun startFragment() {
            scenario = launchFragmentInContainer<TimelineFragment>()
        }
    }

    class TimelineFragmentRobotAssert {
        fun isNotNetworkViewDisplayed() {
            onView(withId(R.id.state_without_conn_timeline))
                .check(matches(isDisplayed()))
        }

        fun isNetworkViewDisplayed() {
            onView(withId(R.id.state_without_conn_timeline))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }

    fun setUp() {
        mockkObject(FragmentActivityUtils)
        every { FragmentActivityUtils.getSupportBar(any()) } returns null
    }

    fun tearDown() {
        unmockkAll()
    }

    fun arrange(func: TimelineFragmentRobotArrange.() -> Unit) =
        TimelineFragmentRobotArrange().apply(func)

    fun act(func: TimelineFragmentRobotAct.() -> Unit) =
        TimelineFragmentRobotAct().apply(func)

    fun assert(func: TimelineFragmentRobotAssert.() -> Unit) =
        TimelineFragmentRobotAssert().apply(func)
}