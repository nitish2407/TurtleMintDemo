package com.githubissuedemo.models

import com.google.gson.annotations.SerializedName


data class Reactions (

  @SerializedName("url"         ) var url        : String? = null,
  @SerializedName("total_count" ) var totalCount : Int?    = null,
  @SerializedName("laugh"       ) var laugh      : Int?    = null,
  @SerializedName("hooray"      ) var hooray     : Int?    = null,
  @SerializedName("confused"    ) var confused   : Int?    = null,
  @SerializedName("heart"       ) var heart      : Int?    = null,
  @SerializedName("rocket"      ) var rocket     : Int?    = null,
  @SerializedName("eyes"        ) var eyes       : Int?    = null

)