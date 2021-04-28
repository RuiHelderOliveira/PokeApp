package com.example.pokeapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.network.PokemonApi
import com.example.pokeapp.network.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel private constructor(private val pokemonService: PokemonService): ViewModel() {

    private var _pokemonLiveData: MutableLiveData<Pokemon> = MutableLiveData<Pokemon>()
    private var pokemonLiveData: LiveData<Pokemon> = _pokemonLiveData

    fun getPokemon(keyword: String?) {
        if (keyword != null) {
            pokemonService.getPokemon(keyword).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.body() != null) {
                        _pokemonLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    _pokemonLiveData.postValue(null)
                }
            })
        }
    }

    fun getPokemonModelLiveData(): LiveData<Pokemon> {
        return pokemonLiveData
    }

    internal class PokemonViewModelFactory(
        private val pokemonService: PokemonService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokemonViewModel(
                pokemonService
            ) as T
        }
    }
}