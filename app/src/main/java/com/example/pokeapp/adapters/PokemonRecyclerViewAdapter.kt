package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.fragments.PokemonListFragment
import com.example.pokeapp.models.PokemonDeck
import com.example.pokeapp.models.PokemonNames
import java.util.*
import javax.inject.Inject

class PokemonRecyclerViewAdapter @Inject constructor() :
    RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {

    private lateinit var onClick: PokemonListFragment
    private lateinit var values: Array<PokemonNames>

    /*private val onClickListener = View.OnClickListener { v ->
        val item = v.tag as PokemonDeck.PokemonNames
        if (twoPane) {
            val fragment = PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PokemonDetailFragment.ARG_ITEM_ID, item.name)
                }
            }
            context.beginTransaction()
                .replace(R.id.pokemon_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(v.context, PokemonDetailActivity::class.java).apply {
                putExtra(PokemonDetailFragment.ARG_ITEM_ID, item.name)
            }
            v.context.startActivity(intent)
        }
    }*/

    fun setList(values: Array<PokemonNames>) {
        this.values = values
    }

    fun setOnClick(pokemonListFragment: PokemonListFragment) {
        this.onClick = pokemonListFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = (position + 1).toString()
        holder.nameTextView.text = item.name.toUpperCase(Locale.getDefault())

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClick)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id_text_view)
        val nameTextView: TextView = view.findViewById(R.id.name_text_view)
    }
}