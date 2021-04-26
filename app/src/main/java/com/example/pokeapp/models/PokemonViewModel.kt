package com.example.pokeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.network.PokemonApi
import com.example.pokeapp.network.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    private var pokemonService: PokemonService
    private var pokemonLiveData: MutableLiveData<Pokemon>

    init {
        pokemonService = PokemonApi.create()
        pokemonLiveData = MutableLiveData<Pokemon>()
    }

    fun getPokemon(keyword: String?) {
        if (keyword != null) {
            pokemonService.getPokemon(keyword).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.body() != null) {
                        pokemonLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    pokemonLiveData.postValue(null)
                }
            })
        }
    }

    fun getPokemonModelLiveData(): LiveData<Pokemon> {
        return pokemonLiveData
    }
}