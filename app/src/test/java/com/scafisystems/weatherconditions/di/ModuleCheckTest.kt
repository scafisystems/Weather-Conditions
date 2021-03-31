package com.scafisystems.weatherconditions.di

import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules

/**
 * Copyright (c) Created by André Scafi on 31/3/2021.
 */
@Category(CheckModuleTest::class)
class ModuleCheckTest : AutoCloseKoinTest() {

    @Test
    fun checkModules() = checkModules {
        modules(appModule)
    }
}