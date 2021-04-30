package com.example.pokeapp.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.activities.PokemonDetailActivity
import com.example.pokeapp.activities.PokemonListActivity
import com.example.pokeapp.fragments.PokemonDetailFragment
import com.example.pokeapp.models.PokemonDeck
import javax.inject.Inject

class PokemonRecyclerViewAdapter @Inject constructor(
    private val parentActivity: PokemonListActivity,
    private val values: Array<PokemonDeck.PokemonNames>,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<PokemonRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as PokemonDeck.PokemonNames
        if (twoPane) {
            val fragment = PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(PokemonDetailFragment.ARG_ITEM_ID, item.name)
                }
            }
            parentActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.pokemon_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(v.context, PokemonDetailActivity::class.java).apply {
                putExtra(PokemonDetailFragment.ARG_ITEM_ID, item.name)
            }
            v.context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = (position + 1).toString()
        holder.nameTextView.text = item.name.toUpperCase()

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id_text_view)
        val nameTextView: TextView = view.findViewById(R.id.name_text_view)
    }
}