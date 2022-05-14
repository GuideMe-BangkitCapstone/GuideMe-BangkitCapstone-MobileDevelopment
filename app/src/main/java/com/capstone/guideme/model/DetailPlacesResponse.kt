package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class DetailPlacesResponse(

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("photo_url")
	val photoUrl: String? = null,

	@field:SerializedName("place_id")
	val placeId: Int? = null
)
