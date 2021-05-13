package com.example.pokeapp.activities

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenario : ActivityScenarioRule<SplashActivity> = ActivityScenarioRule(
        SplashActivity::class.java
    )

    @Before
    fun setUp() {
        hiltRule.inject()
        Intents.init()
    }

    @Test
    fun startNextActivity() {

        val scenario = activityScenario.scenario

        assertNotNull(activityScenario);

        scenario.moveToState(Lifecycle.State.RESUMED)

        ActivityScenario.launch(MainActivity::class.java)
        //intended(hasComponent(SplashActivity::class.java.name))

        Intents.release()

        scenario.moveToState(Lifecycle.State.DESTROYED)

        scenario.close()
    }

    //@After
    //fun finish() {
    //    Intents.release()
    //}
}