package com.capstone.guideme.ui.preview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.guideme.api.ApiConfig
import com.capstone.guideme.model.DetectionResponse
import com.capstone.guideme.model.User
import com.capstone.guideme.utils.UserPreference
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviewViewModel(private val pref: UserPreference) : ViewModel() {

    private val _response = MutableLiveData<DetectionResponse>()
    val response: LiveData<DetectionResponse> = _response
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(): LiveData<User> {
        return pref.getUser().asLiveData()
    }

    fun getDetection(
        token: String,
        imageMultipart: MultipartBody.Part,
    ) {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .detection("Bearer $token", imageMultipart)
        client.enqueue(object : Callback<DetectionResponse> {
            override fun onResponse(
                call: Call<DetectionResponse>,
                response: Response<DetectionResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _response.value = response.body()
                } else {
                    _isLoading.value = false
                    val jsonObject = JSONObject(response.errorBody()!!.charStream().readText())
                    _response.value =
                        DetectionResponse(
                            null,
                            jsonObject.getBoolean("error"),
                            jsonObject.getString("message")
                        )
                    Log.e("AddStoryActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetectionResponse>, t: Throwable) {
                Log.e("PreviewViewModel", "onFailure: ${t.message.toString()}")
            }
        })
    }

}