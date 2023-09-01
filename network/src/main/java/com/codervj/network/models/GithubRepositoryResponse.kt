package com.codervj.network.models

import com.google.gson.annotations.SerializedName

data class GithubRepositoryResponse(
    @SerializedName("id")
    var id : Int = 0,

    @SerializedName("name")
    var name : String = "",

    @SerializedName("html_url")
    var url : String = "",

    @SerializedName("description")
    var description : String? = "",

    @SerializedName("visibility")
    var visibility: String = ""
)
