package com.example.pokeapp.fragments

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pokeapp.R
import com.example.pokeapp.adapters.PokemonRecyclerViewAdapter
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class PokemonListFragmentTest {

    @Before
    fun setUp() {
        // Populate @Inject fields in test class
        hiltRule.inject()
        AccessibilityChecks.enable()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.pokeapp", appContext.packageName)
    }

    @Test
    fun getPokemons() {
    }

    @Test
    fun setPokemons() {
    }

    @Test
    fun getAdapter() {
    }

    @Test
    fun setAdapter() {
    }

    @Test
    fun onCreateView() {
    }

    @Test
    fun onClick() {
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testNavigationToDetail() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        val fragmentArgs = Bundle().apply {
            putString(PokemonDetailFragment.ARG_ITEM_ID, "")
        }

        // Create a graphical FragmentScenario for the TitleScreen
        val listScenario = launchFragmentInContainer<PokemonListFragment>(fragmentArgs)

        listScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.pokemon_list)).perform(actionOnItemAtPosition<PokemonRecyclerViewAdapter.ViewHolder>(1, click()))

        // Confirm nav to DetailFragment and display title
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.pokemon_list)
    }
}