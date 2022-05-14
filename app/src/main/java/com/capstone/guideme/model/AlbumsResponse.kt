package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(

	@field:SerializedName("listPhoto")
	val listPhoto: List<ListPhotoItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ListPhotoItem(

	@field:SerializedName("photo_id")
	val photoId: Int? = null,

	@field:SerializedName("photo_url")
	val photoUrl: String? = null,

	@field:SerializedName("place_id")
	val placeId: Int? = null
)
