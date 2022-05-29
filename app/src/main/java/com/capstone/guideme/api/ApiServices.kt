package com.capstone.guideme.api

import com.capstone.guideme.model.*
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

    @GET("auth/getdetail")
    fun getUserDetail(
        @Query("user_id") userId: Int
    ): Call<User>

    @GET("get/allplaces")
    fun findAllPlaces(): Call<PlacesResponse>

    @GET("get/place")
    fun getDetailPlaces(
        @Header("x-access-tokens") token: String,
        @Query("name") name: String
    ): Call<DetailPlacesResponse>

    @GET("get/articles")
    fun getDetailArticle(
        @Header("x-access-tokens") token: String,
        @Query("place_id") place_id: Int
    ): Call<ArticleResponse>

    @GET("get/albums")
    fun getDetailAlbums(
        @Header("x-access-tokens") token: String,
        @Query("place_id") place_id: Int
    ): Call<AlbumsResponse>

    @GET("get/visithistory")
    fun getUserHistory(
        @Header("x-access-tokens") token: String,
        @Query("user_id") user_id: Int
    ): Call<HistoryResponse>
}