package com.example.pokeapp.network

import com.example.pokeapp.models.Pokemon
import com.example.pokeapp.models.PokemonDeck

import retrofit2.http.GET
import retrofit2.Call;
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/")
    fun getPokemons(): Call<PokemonDeck>

    @GET("pokemon/{id}/")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon/{name}/")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>
}