package com.example.pokeapp.activities

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTest {

    @Before
    fun setUp() {
    }

    @Test
    fun startNextActivity() {
        Intents.init()

        val activityScenario: ActivityScenario<MainActivity> =
            ActivityScenario.launch(MainActivity::class.java)

        assertNotNull(activityScenario);

        activityScenario.moveToState(Lifecycle.State.RESUMED)

        intended(hasComponent(MainActivity::class.java.name))

        Intents.release()

        activityScenario.moveToState(Lifecycle.State.DESTROYED)
    }
}