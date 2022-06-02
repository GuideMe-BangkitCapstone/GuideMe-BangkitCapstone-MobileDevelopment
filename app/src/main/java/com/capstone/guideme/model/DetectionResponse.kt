package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class DetectionResponse(

	@field:SerializedName("place_name")
	val placeName: String? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
