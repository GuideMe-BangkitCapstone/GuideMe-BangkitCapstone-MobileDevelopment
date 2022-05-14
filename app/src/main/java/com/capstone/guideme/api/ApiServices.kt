package com.capstone.guideme.api

import com.capstone.guideme.model.SigninResponse
import com.capstone.guideme.model.SignupResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @FormUrlEncoded
    @POST("auth/register")
    fun signUp(
        @Field("fullname") fullname: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SignupResponse>

    @FormUrlEncoded
    @POST("auth/login")
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SigninResponse>


}