package com.example.pokeapp.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokeapp.di.RetrofitModule
import com.example.pokeapp.network.PokemonService
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Call
import retrofit2.Response

class PokemonDeckViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    /*companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }*/

    @Mock
    lateinit var pokemonApi: PokemonService

    @Mock
    lateinit var observer: Observer<PokemonDeck>

    @Mock
    var call: Call<PokemonDeck>? = null

    @Mock
    var response: Response<PokemonDeck>? = null

    lateinit var pokemonDeckViewModel: PokemonDeckViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        //initMocks(this)
        pokemonApi = RetrofitModule.provideService(RetrofitModule.provideRetrofit())
        pokemonDeckViewModel = PokemonDeckViewModel(pokemonApi)
    }

    @Test
    fun testNull() {
        assertNotNull(pokemonDeckViewModel.getPokemons())
    }


    @Test
    fun getPokemonsServiceTest() {
        // mock data
        val pokemonDeck = PokemonDeck(1118, "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20", null, arrayOf(
            PokemonNames("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
        ))

        `when`(response!!.body()).thenReturn(pokemonDeck)

        // assert that the name matches
        assert(pokemonDeckViewModel.pokemonLiveData.value!!.count == 1118)
        assert(pokemonDeckViewModel.pokemonLiveData.value!!.results[0].name.equals("bulbasaur"))
    }
}