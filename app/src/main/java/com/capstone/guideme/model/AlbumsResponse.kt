package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(

	@field:SerializedName("listPhoto")
	val listPhoto: List<ListPhotoItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ListPhotoItem(

	@field:SerializedName("photo_id")
	val photoId: Int,

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("place_id")
	val placeId: Int
)
