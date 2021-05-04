package com.example.pokeapp.fragments

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class PokemonListFragmentTest {

    @Before
    fun setUp() {
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
}