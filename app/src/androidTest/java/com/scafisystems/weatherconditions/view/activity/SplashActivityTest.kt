package com.scafisystems.weatherconditions.view.activity

import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasBackground
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.scafisystems.weatherconditions.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Copyright (c) Created by André Scafi on 28/3/2021.
 */

class SplashActivityTest{

    @get:Rule
    val activityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.scafisystems.weatherconditions", appContext.packageName)
    }

    @Test
    fun should_show_splash_activity_when_start(){
        onView(ViewMatchers.withId(R.id.imageView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun should_not_show_splash_activity_after_3_seconds(){
        onView(ViewMatchers.withId(R.id.imageView))
            .check(matches(isDisplayed()))
        SystemClock.sleep(5000)
        onView(ViewMatchers.withId(R.id.select_city_edit))
            .check(matches(isDisplayed()))
    }
}