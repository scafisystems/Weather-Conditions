package com.scafisystems.weatherconditions.util

import org.junit.Assert.*
import org.junit.Test

/**
 * Copyright (c) Created by André Scafi on 31/3/2021.
 */
class UtilTest(){

    @Test
    fun should_return_correct_data_format(){
        val dataTxt = "2021-03-27 12:00:00"
        val convertDate = dataTxt.toDate()
        assertEquals("Sat Mar 27 13:00:00 CET 2021", convertDate.toString())
    }

    @Test
    fun should_return_correct_string_data_format(){
        val dataTxt = "2021-03-27 12:00:00"
        val convertDate = dataTxt.toDate()
        val formatTo = convertDate.formatTo("dd-MMM-yy")
        assertEquals("27-mar-21", formatTo)
    }

    @Test
    fun should_format_to_celsus(){
        val temp = "25"
        val convertTemp= temp.toCelsus()
        assertEquals("25 °C", convertTemp)
    }

}