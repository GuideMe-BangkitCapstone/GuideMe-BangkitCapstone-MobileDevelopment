package com.capstone.guideme.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.guideme.api.ApiConfig
import com.capstone.guideme.model.ListPlacesItem
import com.capstone.guideme.model.PlacesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _listPlaces = MutableLiveData<List<ListPlacesItem>>()
    val listPlaces: LiveData<List<ListPlacesItem>> = _listPlaces

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findPlaces() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().findAllPlaces()

        client.enqueue(object : Callback<PlacesResponse> {
            override fun onResponse(
                call: Call<PlacesResponse>,
                response: Response<PlacesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listPlaces.value = response.body()?.listPlaces!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
}