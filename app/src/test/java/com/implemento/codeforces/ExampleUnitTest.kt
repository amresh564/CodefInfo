package com.implemento.codeforces

import com.implemento.codeforces.model.UserInfoService
import com.implemento.codeforces.model.pojo.UserInfo
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
   @Test
   fun testUserInfo()
   {
       val handle  = "amresh564"
       val BASE_URL = "https://codeforces.com"

       val retrofitRequest = Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .build().create(UserInfoService::class.java)

       val response = retrofitRequest.getUserSubmission(handle).execute()
       println(response.body())
   }
}