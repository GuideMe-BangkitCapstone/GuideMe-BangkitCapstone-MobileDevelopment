package com.capstone.guideme.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.guideme.api.ApiConfig
import com.capstone.guideme.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _userHistory = MutableLiveData<List<ListHistoryItem>>()
    val userHistory: LiveData<List<ListHistoryItem>> = _userHistory

    private val _deleteResponse = MutableLiveData<DeleteResponse>()
    val deleteResponse: LiveData<DeleteResponse> = _deleteResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUserDetail(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail("Bearer $token")

        client.enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUserVisitHistory(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserHistory("Bearer $token")

        client.enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userHistory.value = response.body()?.listHistory
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun deleteVisitHistory(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().deleteHistory("Bearer $token")

        client.enqueue(object : Callback<DeleteResponse> {
            override fun onResponse(
                call: Call<DeleteResponse>,
                response: Response<DeleteResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _deleteResponse.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TAG = "ProfileViewModel"
    }

}