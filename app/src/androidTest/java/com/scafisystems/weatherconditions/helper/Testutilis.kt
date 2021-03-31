package com.scafisystems.weatherconditions.helper

import org.junit.Assert

/**
 * Copyright (c) Created by André Scafi on 28/3/2021.
 */


    /**
     * Wait for the specific time using [Thread.sleep]
     * @param milliseconds The time we want to wait for in millis
     */
    fun waitFor(milliseconds: Int) {
        try {
            Thread.sleep(milliseconds.toLong())
        } catch (e: Exception) {
            Assert.fail()
        }

    }

    /**
     * Wait for a condition to be true
     * @param condition The condition we want to be true
     * *
     * @param time      The time to wait for
     */
    fun waitForCondition(condition: Condition, time: Int) {
        var timeWaited = 0
        while (timeWaited < time) {
            if (condition.isSatisfied) {
                return
            }
            waitFor(200)
            timeWaited += 200
        }
    }

    interface Condition {
        val isSatisfied: Boolean
    }
