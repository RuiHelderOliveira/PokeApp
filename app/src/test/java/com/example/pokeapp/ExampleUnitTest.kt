package com.example.pokeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pokeapp.models.Pokemon
import com.example.pokeapp.models.PokemonDeckViewModel
import com.example.pokeapp.models.PokemonViewModel
import com.example.pokeapp.network.PokemonService
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    /*@get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var apiServices: PokemonService? = null

    @Mock
    var apiClient: PokemonApi? = null
    private var viewModel: PokemonViewModel? = null
    private var deckViewModel: PokemonDeckViewModel? = null

    @Mock
    var observer: Observer<Pokemon>? = null

    @Mock
    var lifecycleOwner: LifecycleOwner? = null
    var lifecycle: Lifecycle? = null*/

    /*@Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = lifecycleOwner?.let { LifecycleRegistry(it) }
        viewModel = apiServices?.let { PokemonViewModel() }
        viewModel?.getPokemonModelLiveData()?.observeForever(observer!!)
    }

    @Test
    fun testNull() {
        `when`(apiServices?.getPokemon("ditto")).thenReturn(null)
        assertNotNull(viewModel?.getPokemonModelLiveData())
        assertTrue(viewModel?.getPokemonModelLiveData()?.hasObservers() == true)
    }*/

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}