package com.example.pokeapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentPokemonDetailBinding
import com.example.pokeapp.models.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * A fragment representing a single Pokemon detail screen.
 */
@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    companion object {
        private const val TAG = "PokemonDetailFragment"

        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: String? = null

    private val viewModel: PokemonViewModel by viewModels()

    private lateinit var _binding: FragmentPokemonDetailBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    //@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getString(ARG_ITEM_ID)
                activity?.findViewById<Toolbar>(R.id.toolbar)?.title =
                    it.getString(ARG_ITEM_ID)?.toUpperCase(Locale.getDefault())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        this@PokemonDetailFragment.activity?.let { pokemon ->
            viewModel.pokemonLiveData.observe(pokemon) { response ->
                if (response != null) {
                    Log.d(TAG, response.toString())

                    // Show the model content as text in a TextView.
                    response.let {
                        _binding.idAttrTextView.text = it. id.toString()
                        _binding.experienceAttrTextView.text = it.base_experience.toString()
                        _binding.heightAttrTextView.text = it.height.toString()
                        _binding.isDefaultAttrTextView.text = it.is_default.toString()
                        _binding.orderAttrTextView.text = it.order.toString()
                        _binding.weightAttrTextView.text = it.weight.toString()
                    }
                }
            }
        }
        viewModel.getPokemon(item)

        return binding.root
    }
}