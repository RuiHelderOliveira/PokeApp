package com.example.pokeapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.adapters.PokemonRecyclerViewAdapter
import com.example.pokeapp.databinding.ActivityPokemonListBinding
import com.example.pokeapp.models.PokemonDeck
import com.example.pokeapp.models.PokemonDeckViewModel

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PokemonDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PokemonListActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "PokemonListActivity"
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var binding: ActivityPokemonListBinding

    private val viewModel: PokemonDeckViewModel by viewModels()
    private lateinit var pokemons: Array<PokemonDeck.PokemonNames>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.pokemon_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        viewModel.getPokemonModelLiveData().observe(this) { response ->
            if (response != null) {
                Log.d(TAG, response.toString())
                pokemons = response.results
                setupRecyclerView(findViewById(R.id.pokemon_list))
            }
        }
        viewModel.getPokemons()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = PokemonRecyclerViewAdapter(this@PokemonListActivity, pokemons, twoPane)
    }
}