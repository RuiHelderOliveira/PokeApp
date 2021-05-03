package com.example.pokeapp.di

import com.example.pokeapp.BuildConfig
import com.example.pokeapp.network.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
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
    fun provideBlogService(retrofit: Retrofit.Builder): PokemonService {
        return retrofit.build().create(PokemonService::class.java)
    }
}