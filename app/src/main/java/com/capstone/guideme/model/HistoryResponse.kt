package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("listHistory")
	val listHistory: List<ListHistoryItem>
)

data class ListHistoryItem(

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("history_id")
	val historyId: Int,

	@field:SerializedName("places.place_id")
	val placesPlaceId: Int,

	@field:SerializedName("place_id")
	val placeId: Int
)
