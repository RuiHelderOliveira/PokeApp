package com.example.pokeapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.pokeapp.R
import com.example.pokeapp.adapters.PokemonRecyclerViewAdapter
import com.example.pokeapp.databinding.FragmentPokemonListBinding
import com.example.pokeapp.models.PokemonDeck
import com.example.pokeapp.models.PokemonDeckViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a Pokemon list screen.
 */
@AndroidEntryPoint
class PokemonListFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val TAG = "PokemonListFragment"
    }

    private lateinit var _binding: FragmentPokemonListBinding

    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding

    private val viewModel: PokemonDeckViewModel by viewModels()
    lateinit var pokemons: Array<PokemonDeck.PokemonNames>

    @Inject
    var adapter: PokemonRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        activity?.let {
            viewModel.pokemonLiveData.observe(it) { response ->
                if (response != null) {
                    Log.d(TAG, response.toString())
                    pokemons = response.results
                    binding.pokemonList.adapter = adapter
                }
            }
        }
        viewModel.getPokemons()

        return binding.root
    }

    override fun onClick(v: View?) {
        val item = v?.tag as PokemonDeck.PokemonNames
        val bundle = bundleOf(PokemonDetailFragment.ARG_ITEM_ID to item.name)


        view?.let { it1 ->
            Navigation.findNavController(it1).navigate(R.id.action_list_screen_to_detail, bundle)
        }
    }
}