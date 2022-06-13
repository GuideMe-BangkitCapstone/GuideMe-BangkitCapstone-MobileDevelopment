package com.capstone.guideme.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.guideme.api.ApiConfig
import com.capstone.guideme.model.*
import com.capstone.guideme.utils.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val pref: UserPreference) : ViewModel() {
    private val _detailPlace = MutableLiveData<DetailPlacesResponse>()
    val detailPlace: LiveData<DetailPlacesResponse> = _detailPlace

    private val _detailArticle = MutableLiveData<List<ListArticleItem>>()
    val detailArticle: LiveData<List<ListArticleItem>> = _detailArticle

    private val _detailAlbums = MutableLiveData<List<ListPhotoItem>>()
    val detailAlbums: LiveData<List<ListPhotoItem>> = _detailAlbums

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun getDetailPlace(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailPlaces(name)
        client.enqueue(object : Callback<DetailPlacesResponse> {
            override fun onResponse(
                call: Call<DetailPlacesResponse>,
                response: Response<DetailPlacesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailPlace.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailPlacesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    internal fun getDetailAlbum(placeId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailAlbums(placeId)
        client.enqueue(object : Callback<AlbumsResponse> {
            override fun onResponse(
                call: Call<AlbumsResponse>,
                response: Response<AlbumsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailAlbums.value = response.body()?.listPhoto
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AlbumsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    internal fun getDetailArticle(placeId: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailArticle(placeId)
        client.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailArticle.value = response.body()?.listArticle
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailModel"
    }
}