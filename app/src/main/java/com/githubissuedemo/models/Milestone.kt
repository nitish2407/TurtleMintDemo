package com.githubissuedemo.models

import com.google.gson.annotations.SerializedName


data class Milestone (

  @SerializedName("url"         ) var url         : String?  = null,
  @SerializedName("html_url"    ) var htmlUrl     : String?  = null,
  @SerializedName("labels_url"  ) var labelsUrl   : String?  = null,
  @SerializedName("id"          ) var id          : Int?     = null,
  @SerializedName("node_id"     ) var nodeId      : String?  = null,
  @SerializedName("number"      ) var number      : Int?     = null,
  @SerializedName("title"       ) var title       : String?  = null,
  @SerializedName("description" ) var description : String?  = null,
  @SerializedName("creator"     ) var creator     : Creator? = Creator()

)