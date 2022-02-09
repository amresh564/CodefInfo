package com.implemento.codeforces.model.pojo


import com.google.gson.annotations.SerializedName

data class SubmissionInfo(
    @SerializedName("result")
    val result: List<ResultXX>,
    @SerializedName("status")
    val status: String
)