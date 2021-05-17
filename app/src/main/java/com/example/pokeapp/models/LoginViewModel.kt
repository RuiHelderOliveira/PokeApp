package com.example.pokeapp.models

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.pokeapp.R
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    var emailAddress = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var userMutableLiveData: MutableLiveData<User>? = null

    fun getUser(): MutableLiveData<User>? {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData<User>()
        }
        return userMutableLiveData
    }

    fun onClick(view: View?) {
        val loginUser = User(emailAddress.value, password.value)
        userMutableLiveData!!.setValue(loginUser)
    }
}