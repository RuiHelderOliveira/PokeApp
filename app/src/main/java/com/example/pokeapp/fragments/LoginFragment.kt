package com.example.pokeapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentLoginBinding
import com.example.pokeapp.models.LoginViewModel
import com.example.pokeapp.models.User
import java.util.*

class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    // with the backing property of the kotlin we extract
    // the non null value of the _binding
    private val binding get() = _binding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        _binding.viewModel = viewModel
        _binding.executePendingBindings()

        viewModel.getUser()?.observe(viewLifecycleOwner, object : Observer<User?> {
            override fun onChanged(loginUser: User?) {
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser)?.email)) {
                    binding.usernameEditText.setError("Enter an E-Mail Address")
                    binding.usernameEditText.requestFocus()
                } else if (!(loginUser?.isEmailValid())!!) {
                    binding.usernameEditText.setError("Enter a Valid E-mail Address")
                    binding.usernameEditText.requestFocus()
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).password)) {
                    binding.passwordEditText.setError("Enter a Password")
                    binding.passwordEditText.requestFocus()
                } else if (!(loginUser.isPasswordLengthGreaterThan5())) {
                    binding.passwordEditText.setError("Enter at least 6 Digit password")
                    binding.passwordEditText.requestFocus()
                } else {
                    Navigation.findNavController(binding.loginButton).navigate(R.id.action_login_screen_to_pokemon_list)
                }
            }
        })
        return binding.root
    }
}