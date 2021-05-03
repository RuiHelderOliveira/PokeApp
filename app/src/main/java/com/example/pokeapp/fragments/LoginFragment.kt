package com.example.pokeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentLoginBinding
import com.example.pokeapp.databinding.FragmentPokemonDetailBinding

class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener(View.OnClickListener {
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_login_screen_to_pokemon_list) }
        })

        return binding.root
    }
}