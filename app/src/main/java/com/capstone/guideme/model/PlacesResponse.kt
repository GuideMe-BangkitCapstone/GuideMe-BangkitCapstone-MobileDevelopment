package com.capstone.guideme.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlacesResponse(

	@field:SerializedName("listPlaces")
	val listPlaces: List<ListPlacesItem>? = null,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class ListPlacesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("photo_url")
	val photoUrl: String
) : Parcelable
