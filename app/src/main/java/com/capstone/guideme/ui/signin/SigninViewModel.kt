package com.capstone.guideme.ui.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.guideme.api.ApiConfig
import com.capstone.guideme.model.SigninResponse
import com.capstone.guideme.utils.UserPreference
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninViewModel(private val pref: UserPreference): ViewModel() {
    private val _response = MutableLiveData<SigninResponse>()
    val response: LiveData<SigninResponse> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun signInUser(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().signIn(email, password)
        client.enqueue(object : Callback<SigninResponse> {
            override fun onResponse(
                call: Call<SigninResponse>,
                response: Response<SigninResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _response.value = response.body()
                    viewModelScope.launch {
                        response.body()?.loginResult?.let {
                            pref.setUser(it)
                        }
                    }
                } else {
                    _isLoading.value = false
                    val jsonObject = JSONObject(response.errorBody()!!.charStream().readText())
                    _response.value =
                        SigninResponse(
                            null,
                            jsonObject.getBoolean("error"),
                            jsonObject.getString("message")
                        )
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "SignInViewModel"
    }
}