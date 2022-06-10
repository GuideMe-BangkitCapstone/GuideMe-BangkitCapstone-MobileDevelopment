package com.capstone.guideme.api

import com.capstone.guideme.model.*
import okhttp3.MultipartBody
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
        @Header("Authorization") token: String
    ): Call<User>

    @GET("get/allplaces")
    fun findAllPlaces(): Call<PlacesResponse>

    @GET("get/place")
    fun getDetailPlaces(
        @Query("name") name: String
    ): Call<DetailPlacesResponse>

    @GET("get/articles")
    fun getDetailArticle(
        @Query("place_id") place_id: Int
    ): Call<ArticleResponse>

    @GET("get/albums")
    fun getDetailAlbums(
        @Query("place_id") place_id: Int
    ): Call<AlbumsResponse>

    @GET("get/visithistory")
    fun getUserHistory(
        @Header("Authorization") token: String
    ): Call<HistoryResponse>

    @Multipart
    @POST("detection")
    fun detection(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Call<DetectionResponse>

    @DELETE("get/deletehistory")
    fun deleteHistory(
        @Header("Authorization") token: String,
    ): Call<DeleteResponse>

}