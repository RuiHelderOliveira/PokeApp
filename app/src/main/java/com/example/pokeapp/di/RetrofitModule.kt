package com.example.pokeapp.di

import com.example.pokeapp.network.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        val pokemonServiceBaseUrl = "https://pokeapi.co/api/v2/"

        return Retrofit.Builder()
            .baseUrl(pokemonServiceBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): PokemonService {
        return retrofit.build().create(PokemonService::class.java)
    }
}