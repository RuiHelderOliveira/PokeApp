package com.example.pokeapp.di

import com.example.pokeapp.BuildConfig
import com.example.pokeapp.network.PokemonService
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Reusable
    fun provideRetrofit(): Retrofit.Builder {
        val pokemonServiceBaseUrl = "https://pokeapi.co/api/v2/"

        return Retrofit.Builder()
            .baseUrl(pokemonServiceBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Reusable
    fun provideBlogService(retrofit: Retrofit.Builder): PokemonService {
        return retrofit.build().create(PokemonService::class.java)
    }
}