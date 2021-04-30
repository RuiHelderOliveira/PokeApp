package com.example.pokeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.network.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonDeckViewModel @Inject constructor(private val pokemonService: PokemonService) : ViewModel() {

    private val _pokemonLiveData: MutableLiveData<PokemonDeck> = MutableLiveData<PokemonDeck>()
    val pokemonLiveData: LiveData<PokemonDeck>
        get() = _pokemonLiveData

    fun getPokemons() {
            pokemonService.getPokemons().enqueue(object : Callback<PokemonDeck> {
                override fun onResponse(call: Call<PokemonDeck>, response: Response<PokemonDeck>) {
                    if (response.body() != null) {
                        _pokemonLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PokemonDeck>, t: Throwable) {
                    _pokemonLiveData.postValue(null)
                }
            })
    }
}