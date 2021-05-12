package com.example.pokeapp.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.pokeapp.network.PokemonService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonDeckViewModelTest {

    companion object {
        const val name = "bulbasaur"
    }

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var pokemonApi: PokemonService

    @Mock
    private lateinit var observer: Observer<PokemonDeck>

    lateinit var pokemonViewModel: PokemonDeckViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        initMocks(this)
        pokemonViewModel = PokemonDeckViewModel(pokemonApi)
        pokemonViewModel.pokemonLiveData.observeForever(observer)
    }

    @Test
    fun getPokemonsServiceTest() {
        // mock data

        val pokemonsDeck = PokemonDeck(
            1118,
            "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            null,
            arrayOf(PokemonNames("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"))
        )

        pokemonViewModel.pokemonLiveData.observeForever {
            assertNotNull(it)
            assert(it.count == pokemonsDeck.count)
            assert(it.next.equals(pokemonsDeck.next))
            assert(it.previous.equals(pokemonsDeck.previous))
            assert(it.results[0].name == pokemonsDeck.results[0].name)
        }
    }
}