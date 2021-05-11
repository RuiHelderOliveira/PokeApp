package com.example.pokeapp.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokeapp.di.RetrofitModule
import com.example.pokeapp.network.PokemonService
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Call
import retrofit2.Response

class PokemonViewModelTest {

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
    lateinit var observer: Observer<Pokemon>

    @Mock
    var call: Call<Pokemon>? = null

    @Mock
    var response: Response<Pokemon>? = null

    lateinit var pokemonViewModel: PokemonViewModel

    val name = "bulbasaur"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        //initMocks(this)
        pokemonApi = RetrofitModule.provideService(RetrofitModule.provideRetrofit())
        pokemonViewModel = PokemonViewModel(pokemonApi)
    }

    @Test
    fun testNull() {
        assertNotNull(pokemonViewModel.getPokemon(name))
    }


    @Test
    fun getPokemonsServiceTest() {
        // mock data

        `when`(response!!.body()).thenReturn(Pokemon(1, "bulbasaur", 64, 7, true, 1,69, null))

        // assert that the name matches
        assert(pokemonViewModel.pokemonLiveData.value!!.id == 1)
        assert(pokemonViewModel.pokemonLiveData.value!!.name.equals("bulbasaur"))
        assert(pokemonViewModel.pokemonLiveData.value!!.base_experience == 64)
        assert(pokemonViewModel.pokemonLiveData.value!!.height == 7)
        assertTrue(pokemonViewModel.pokemonLiveData.value!!.is_default == true)
        assert(pokemonViewModel.pokemonLiveData.value!!.order == 1)
        assert(pokemonViewModel.pokemonLiveData.value!!.weight == 69)
    }
}