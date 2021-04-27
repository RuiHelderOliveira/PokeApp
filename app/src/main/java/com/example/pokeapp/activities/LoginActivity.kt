package com.example.pokeapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        binding.loginLayout.loginButton.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@LoginActivity, PokemonListActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}