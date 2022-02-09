package com.implemento.codeforces.model

import com.implemento.codeforces.model.pojo.ParticipatedContestInfo
import com.implemento.codeforces.model.pojo.Result
import com.implemento.codeforces.model.pojo.SubmissionInfo
import com.implemento.codeforces.model.pojo.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserInfoService {
    @GET("/api/user.info")
    fun getUserInfo(@Query("handles") handle: String) : Call<UserInfo>

    @GET("/api/user.rating")
    fun getUserContest(@Query("handle")handle: String) : Call<ParticipatedContestInfo>

    @GET("/api/user.status")
    fun getUserSubmission(@Query("handle")handle: String) : Call<SubmissionInfo>
}