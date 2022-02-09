package com.implemento.codeforces.model.pojo


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("status")
    val status: String
)