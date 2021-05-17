package com.example.pokeapp.models

import android.util.Patterns

data class User(var email: String?, var password: String?) {

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordLengthGreaterThan5(): Boolean {
        return password?.length!! > 5
    }
}