package com.capstone.guideme.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("listArticle")
	val listArticle: List<ListArticleItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ListArticleItem(

	@field:SerializedName("article_id")
	val articleId: Int,

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("article_url")
	val articleUrl: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("place_id")
	val placeId: Int
)
