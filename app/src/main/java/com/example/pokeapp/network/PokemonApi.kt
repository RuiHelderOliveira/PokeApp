package com.example.pokeapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApi {
    fun create(): PokemonService {

        val pokemonServiceBaseUrl = "https://pokeapi.co/api/v2/"

        val retrofit = Retrofit.Builder()
            .baseUrl(pokemonServiceBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokemonService::class.java)
    }
}