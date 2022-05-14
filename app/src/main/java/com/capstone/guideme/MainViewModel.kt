package com.capstone.guideme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.guideme.model.User
import com.capstone.guideme.utils.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun getUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

    fun logOutUser() {
        viewModelScope.launch {
            pref.logOutUser()
        }
    }
}