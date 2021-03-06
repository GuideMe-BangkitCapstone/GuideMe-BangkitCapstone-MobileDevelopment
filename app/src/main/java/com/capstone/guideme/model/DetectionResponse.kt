package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class DetectionResponse(

	@field:SerializedName("place_name")
	val placeName: String?,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,
)
