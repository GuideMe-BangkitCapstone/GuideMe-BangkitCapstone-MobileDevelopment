package com.capstone.guideme.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlacesResponse(

	@field:SerializedName("listPlaces")
	val listPlaces: List<ListPlacesItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class ListPlacesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo_url")
	val photoUrl: String? = null
) : Parcelable
