package com.example.pokeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.network.PokemonApi
import com.example.pokeapp.network.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDeckViewModel : ViewModel() {

    private var pokemonService: PokemonService
    private var pokemonLiveData: MutableLiveData<PokemonDeck>

    init {
        pokemonService = PokemonApi.create()
        pokemonLiveData = MutableLiveData<PokemonDeck>()
    }

    fun getPokemons() {
            pokemonService.getPokemons().enqueue(object : Callback<PokemonDeck> {
                override fun onResponse(call: Call<PokemonDeck>, response: Response<PokemonDeck>) {
                    if (response.body() != null) {
                        pokemonLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PokemonDeck>, t: Throwable) {
                    pokemonLiveData.postValue(null)
                }
            })
    }

    fun getPokemonModelLiveData(): LiveData<PokemonDeck> {
        return pokemonLiveData
    }
}