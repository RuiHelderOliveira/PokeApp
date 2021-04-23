package com.example.pokeapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.models.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonApi {

    companion object {
        private val POKEMON_SERVICE_BASE_URL = "https://pokeapi.co/api/v2/"
    }

    private var pokemonModelLiveData: MutableLiveData<Pokemon> = MutableLiveData<Pokemon>()
    private var pokemonService: PokemonService

    init {
        val pokemonClient = Retrofit.Builder()
            .baseUrl(POKEMON_SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pokemonService = pokemonClient.create(PokemonService::class.java)
    }

    fun getPokemon(keyword: String?) {
        if (keyword != null) {
            pokemonService.getPokemon(keyword)
                .enqueue(object : Callback<Pokemon> {
                    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                        if (response.body() != null) {
                            pokemonModelLiveData.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        pokemonModelLiveData.postValue(null)
                    }

                })
        }
    }

    fun getPokemonLiveData(): LiveData<Pokemon> {
        return pokemonModelLiveData
    }
}