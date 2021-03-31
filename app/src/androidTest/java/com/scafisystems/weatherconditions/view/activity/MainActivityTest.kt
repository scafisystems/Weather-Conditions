package com.scafisystems.weatherconditions.view.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.scafisystems.weatherconditions.R
import com.scafisystems.weatherconditions.helper.Condition
import com.scafisystems.weatherconditions.helper.ToastMatcher
import com.scafisystems.weatherconditions.helper.waitForCondition
import com.scafisystems.weatherconditions.view.adapter.WeatherViewHolder
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Copyright (c) Created by André Scafi on 28/3/2021.
 */
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun should_show_main_activity_when_start() {
        onView(withId(R.id.select_city_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_go_to_details)).check(matches(isDisplayed()))
    }

    @Test
    fun should_show_toast_message_when_edittext_is_empty() {
        onView(withId(R.id.btn_go_to_details)).perform(click())
        onView(withText(R.string.insert_city_name)).inRoot(ToastMatcher())
            .check(matches(isDisplayed()));
    }

    @Test
    fun should_go_details_fragment_when_edit_text_is_not_empty() {
        onView(withId(R.id.select_city_edit)).perform(typeText("Trento"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_go_to_details)).perform(click())
        onView(withId(R.id.fragment_list)).check(matches(isDisplayed()))
    }

    @Test
    fun should_return_to_details_fragment_when_click_return_btn() {
        onView(withId(R.id.select_city_edit)).perform(typeText("Trento"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_go_to_details)).perform(click())
        onView(withId(R.id.fragment_list)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.select_city_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_go_to_details)).check(matches(isDisplayed()))
    }

    @Test
    fun should_show_List_when_enter_in_list_fragment() {
        onView(withId(R.id.select_city_edit)).perform(typeText("Trento"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_go_to_details)).perform(click())
        onView(withId(R.id.fragment_list)).check(matches(isDisplayed()))

        val recyclerView = activityTestRule.activity.findViewById(R.id.my_recycler_view) as RecyclerView
        waitForCondition(object : Condition {
            override val isSatisfied: Boolean
                get() = recyclerView.adapter != null
        }, 5000)
        assertNotNull(recyclerView)
        assertNotNull(recyclerView.adapter)
        assertNotSame(0, recyclerView.adapter?.itemCount)
    }

    @Test
    fun should_open_detail_dialog_when_click_on_list_item() {
        onView(withId(R.id.select_city_edit)).perform(typeText("Trento"))
        closeSoftKeyboard()
        onView(withId(R.id.btn_go_to_details)).perform(click())
        onView(withId(R.id.fragment_list)).check(matches(isDisplayed()))

        val recyclerView = activityTestRule.activity.findViewById(R.id.my_recycler_view) as RecyclerView
        waitForCondition(object : Condition {
            override val isSatisfied: Boolean
                get() = recyclerView.adapter != null
        }, 6000)

        onView(withId(R.id.my_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<WeatherViewHolder>(1, click()))
    }


}