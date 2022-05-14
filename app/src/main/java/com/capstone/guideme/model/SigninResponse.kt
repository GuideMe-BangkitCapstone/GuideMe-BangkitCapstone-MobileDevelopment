package com.capstone.guideme.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SigninResponse(

	@field:SerializedName("loginResult")
	val loginResult: User?,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
) : Parcelable

