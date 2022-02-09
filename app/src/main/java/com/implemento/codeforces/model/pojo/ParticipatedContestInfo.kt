package com.implemento.codeforces.model.pojo


import com.google.gson.annotations.SerializedName

data class ParticipatedContestInfo(
    @SerializedName("result")
    val result: List<ResultX>,
    @SerializedName("status")
    val status: String
)