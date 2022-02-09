package com.implemento.codeforces.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.implemento.codeforces.databinding.ActivitySubmissionBinding
import com.implemento.codeforces.model.UserInfoService
import com.implemento.codeforces.viewModel.SubmissionInfoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SubmissionActivity : AppCompatActivity() {
    lateinit var handle: String
    private lateinit var binding : ActivitySubmissionBinding
    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(UserInfoService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handle = intent.getStringExtra("handle") as String

        GlobalScope.launch (Dispatchers.IO){
            setSubmissionInfo(handle)
        }

    }

    private fun setSubmissionInfo(handle: String) {
            val response = retrofit.getUserSubmission(handle).execute().body()
            runOnUiThread{
                binding.recyclerViewSubmissionInfo.apply {
                    layoutManager = LinearLayoutManager(this@SubmissionActivity)
                    adapter = SubmissionInfoAdapter(response)
                    addItemDecoration(DividerItemDecoration(this@SubmissionActivity,LinearLayoutManager.VERTICAL))
                }
            }
    }
}