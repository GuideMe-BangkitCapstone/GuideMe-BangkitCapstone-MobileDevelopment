package com.capstone.guideme.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.guideme.api.ApiConfig
import com.capstone.guideme.model.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val _response = MutableLiveData<SignupResponse>()
    val response: LiveData<SignupResponse> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun signUpUser(fullname: String, email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().signUp(fullname, email, password)
        client.enqueue(object : Callback<SignupResponse> {
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _response.value = response.body()
                } else {
                    _isLoading.value = false
                    _response.value =
                        SignupResponse(
                            response.body()!!.error,
                            response.body()!!.message
                        )
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "SignUpViewModel"
    }
}