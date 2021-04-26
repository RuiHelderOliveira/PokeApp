package com.example.pokeapp.network

import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.models.Pokemon
import com.example.pokeapp.models.PokemonDeck
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApi {
    fun create(): PokemonService {

        val POKEMON_SERVICE_BASE_URL = "https://pokeapi.co/api/v2/"

        val retrofit = Retrofit.Builder()
            .baseUrl(POKEMON_SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokemonService::class.java)
    }
}