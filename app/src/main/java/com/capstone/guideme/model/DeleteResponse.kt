package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class DeleteResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
