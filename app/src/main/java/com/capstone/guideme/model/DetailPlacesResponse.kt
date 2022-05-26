package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class DetailPlacesResponse(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("place_id")
	val placeId: Int
)
