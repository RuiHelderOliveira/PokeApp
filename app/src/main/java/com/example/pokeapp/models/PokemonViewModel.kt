package com.example.pokeapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.network.PokemonApi


class PokemonViewModel : ViewModel() {

    private var pokemonApi: PokemonApi
    private var PokemonLiveData: LiveData<Pokemon>

    init {
        pokemonApi = PokemonApi()
        PokemonLiveData = pokemonApi.getPokemonLiveData()!!
    }

    fun searchPokemon(keyword: String?) {
        pokemonApi.getPokemon(keyword)
    }

    fun getPokemonModelLiveData(): LiveData<Pokemon> {
        return PokemonLiveData
    }
}