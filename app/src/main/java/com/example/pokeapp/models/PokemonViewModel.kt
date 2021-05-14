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
class PokemonViewModel @Inject constructor(private val pokemonService: PokemonService) : ViewModel() {

    private val _pokemonLiveData: MutableLiveData<Pokemon> = MutableLiveData<Pokemon>()
    val pokemonLiveData: LiveData<Pokemon>
        get() = _pokemonLiveData

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
}