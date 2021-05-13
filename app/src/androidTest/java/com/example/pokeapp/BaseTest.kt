package com.example.pokeapp

import androidx.test.espresso.accessibility.AccessibilityChecks
import org.junit.Before

open class BaseTest {

    @Before
    open fun setUp() {
        // Populate @Inject fields in test class
        AccessibilityChecks.enable()
    }
}