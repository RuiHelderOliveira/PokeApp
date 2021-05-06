package com.example.pokeapp.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokeapp.di.RetrofitModule
import com.example.pokeapp.network.PokemonService
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
@LargeTest
class PokemonViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var service: PokemonService


    private lateinit var viewModel: PokemonViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = RetrofitModule.provideService(RetrofitModule.provideRetrofit())
        viewModel = PokemonViewModel(service)
    }

    @Test
    fun testNull() {
        assertNotNull(viewModel.getPokemon("ditto"))
    }
}