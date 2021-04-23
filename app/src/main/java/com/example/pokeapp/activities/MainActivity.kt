package com.example.pokeapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.pokeapp.databinding.ActivityMainBinding
import com.example.pokeapp.models.Pokemon
import com.example.pokeapp.models.PokemonViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: PokemonViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onStart() {
        super.onStart()

        viewModel.getPokemonModelLiveData().observe(this, object : Observer<Pokemon?> {
            override fun onChanged(response: Pokemon?) {
                if (response != null) {
                    Log.i("Main", response.toString())
                }
            }
        })

        viewModel.searchPokemon("ditto")
    }
}