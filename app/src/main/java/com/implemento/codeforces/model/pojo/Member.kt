package com.implemento.codeforces.model.pojo


import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("handle")
    val handle: String
)