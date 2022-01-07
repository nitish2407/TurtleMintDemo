package com.githubissuedemo.models

import com.google.gson.annotations.SerializedName

data class CommentsResponse (

	@SerializedName("url") val url : String,
	@SerializedName("html_url") val html_url : String,
	@SerializedName("issue_url") val issue_url : String,
	@SerializedName("id") val id : Int,
	@SerializedName("node_id") val node_id : String,
	@SerializedName("user") val user : User,
	@SerializedName("created_at") val created_at : String,
	@SerializedName("updated_at") val updated_at : String,
	@SerializedName("author_association") val author_association : String,
	@SerializedName("body") val body : String,
	@SerializedName("reactions") val reactions : Reactions,
	@SerializedName("performed_via_github_app") val performed_via_github_app : String
)