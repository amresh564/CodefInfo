package com.implemento.codeforces.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.implemento.codeforces.databinding.ActivityMainBinding
import com.implemento.codeforces.model.UserInfoService
import com.implemento.codeforces.viewModel.ContestInfoAdapter
import com.implemento.codeforces.viewModel.UserInfoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Key

class MainActivity : AppCompatActivity() {

    val TAG = "FIND ME"
    lateinit var binding: ActivityMainBinding
    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL).build().create(UserInfoService::class.java)

    lateinit var handle : String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val submissionInfoButton = binding.buttonSubmissionInfo

        binding.editTextHandle.setOnEditorActionListener { v, actionId, event ->
            if((event!=null && event.keyCode==KeyEvent.KEYCODE_ENTER) || (actionId==EditorInfo.IME_ACTION_DONE))
            {
                handle = v.text.toString()
                onClick(handle)
                false
            }
            else
                false
        }

        binding.buttonSubmitHandle.setOnClickListener {
            handle = binding.editTextHandle.text.toString()
            onClick(handle)
        }

        binding.buttonSubmissionInfo.setOnClickListener {
            val intent = Intent(this,SubmissionActivity::class.java)
            intent.putExtra("handle",handle)
            startActivity(intent)
        }
    }

    private fun onClick(handle: String) {
        if (handle != "") {
            GlobalScope.launch(Dispatchers.IO) {
                setUserInfo(handle)
            }
        } else
            Toast.makeText(this, "Please Enter Codeforces Username", Toast.LENGTH_SHORT).show()
    }

    private suspend fun setUserInfo(handle: String) {
        val response = retrofit.getUserInfo(handle).execute().body()

        runOnUiThread {
            binding.recyclerViewUserInfo.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = UserInfoAdapter(response)
            }

            if (response != null) {
                binding.buttonSubmissionInfo.visibility = View.VISIBLE
                binding.recyclerViewContestInfo.visibility = View.VISIBLE

                GlobalScope.launch(Dispatchers.IO) {
                    setUserContest(handle)
                }
            } else {
                Toast.makeText(this, "Username Not Found", Toast.LENGTH_SHORT).show()
                binding.buttonSubmissionInfo.visibility = View.GONE
                binding.recyclerViewContestInfo.visibility = View.GONE
            }

        }
    }

    private fun setUserContest(handle: String) {
        val response = retrofit.getUserContest(handle).execute().body()
        runOnUiThread {
            binding.recyclerViewContestInfo.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ContestInfoAdapter(response!!.result)
                isNestedScrollingEnabled = false
            }
        }
    }
}