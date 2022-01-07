package com.githubissuedemo.models

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class User {

  @SerializedName("login")
  var login: String? = null

  @SerializedName("id")
  var id: Int? = null

  @SerializedName("node_id")
  var nodeId: String? = null

  @SerializedName("avatar_url")
  var avatarUrl: String? = null

  @SerializedName("gravatar_id")
  var gravatarId: String? = null

  @SerializedName("url")
  var url: String? = null

  @SerializedName("html_url")
  var htmlUrl: String? = null

  @SerializedName("followers_url")
  var followersUrl: String? = null

  @SerializedName("following_url")
  var followingUrl: String? = null

  @SerializedName("gists_url")
  var gistsUrl: String? = null

  @SerializedName("starred_url")
  var starredUrl: String? = null

  @SerializedName("subscriptions_url")
  var subscriptionsUrl: String? = null

  @SerializedName("organizations_url")
  var organizationsUrl: String? = null

  @SerializedName("repos_url")
  var reposUrl: String? = null

  @SerializedName("events_url")
  var eventsUrl: String? = null

  @SerializedName("received_events_url")
  var receivedEventsUrl: String? = null

  @SerializedName("type")
  var type: String? = null

  @SerializedName("site_admin")
  var siteAdmin: Boolean? = null

  companion object {


    @JvmStatic
    @BindingAdapter("avatar_url")
    fun loadImage(imageView: ImageView, imageURL: String) {

      Log.e("imsgeurl", imageURL)
      Glide.with(imageView.context)
        .setDefaultRequestOptions(
          RequestOptions()
            .circleCrop()
        )
        .load(imageURL)

        .into(imageView)
    }
    @JvmStatic
    @BindingAdapter("updated_at")
    fun loadTime(textView: TextView, time: String) {

      Log.e("imsgeurl", time)
      textView.text = parseDateToddMMyyyy(time)
    }

    @SuppressLint("SimpleDateFormat")
    private fun parseDateToddMMyyyy(time: String?): String? {
      var str : String?= ""
      val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
      val outputPattern = "MM-dd-yyyy"
      val inputFormat = SimpleDateFormat(inputPattern)
      val outputFormat = SimpleDateFormat(outputPattern)
      var date: Date? = null
      try {
        date = inputFormat.parse(time)
        str = outputFormat.format(date)
        str = "Last updated on $str"
      } catch (e: ParseException) {
        e.printStackTrace()
      }
      return str
    }

  }
}