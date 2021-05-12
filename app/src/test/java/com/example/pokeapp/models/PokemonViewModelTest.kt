package com.example.pokeapp.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.pokeapp.network.PokemonService
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {

    companion object {
        const val name = "bulbasaur"
    }

    @get:Rule
    public var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokemonApi: PokemonService

    @Mock
    private lateinit var observer: Observer<Pokemon>

    lateinit var pokemonViewModel: PokemonViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        initMocks(this)
        pokemonViewModel = PokemonViewModel(pokemonApi)
        pokemonViewModel.pokemonLiveData.observeForever(observer)
    }

    @Test
    fun getPokemonsServiceTest() {
        // mock data

        val pokemon = Pokemon(
            1,
            "bulbasaur",
            64,
            7,
            true,
            1,
            69,
            null
        )

        pokemonViewModel.pokemonLiveData.observeForever {
            assertNotNull(it)
            assert(it.id == pokemon.id)
            assert(it.name.equals(pokemon.name))
            assert(it.base_experience == pokemon.base_experience)
            assert(it.height == pokemon.height)
            assertTrue(it.is_default == pokemon.is_default)
            assert(it.order == pokemon.order)
            assert(it.weight == pokemon.weight)
        }
    }
}