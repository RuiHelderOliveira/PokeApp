package com.example.pokeapp

import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseTest {

    @Before
    open fun setUp() {
        // Populate @Inject fields in test class
        AccessibilityChecks.enable()
    }
}