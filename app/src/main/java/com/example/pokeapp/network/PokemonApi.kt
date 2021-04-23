package com.example.pokeapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokeapp.models.Pokemon
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonApi {
    private val POKEMON_SERVICE_BASE_URL = "https://pokeapi.co/api/v2/"

    private var pokemonService: PokemonService? = null
    private var pokemonModelLiveData: MutableLiveData<Pokemon?>? = null

    fun PokemonApi() {
        pokemonModelLiveData = MutableLiveData<Pokemon?>()
        val client = OkHttpClient.Builder().build()
        pokemonService = Retrofit.Builder()
            .baseUrl(POKEMON_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
    }

    fun searchPokemon(keyword: String?) {
        if (keyword != null) {
            pokemonService?.getPokemon(keyword)?.enqueue(object : Callback<Pokemon> {
                    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                        if (response.body() != null) {
                            pokemonModelLiveData!!.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        pokemonModelLiveData!!.postValue(null)
                    }

                })
        }
    }

    fun getPokemonLiveData(): LiveData<Pokemon?>? {
        return pokemonModelLiveData
    }
}