package com.implemento.codeforces.viewModel

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implemento.codeforces.databinding.UserInfoBinding
import com.implemento.codeforces.model.pojo.UserInfo
import com.implemento.codeforces.views.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class UserInfoAdapter(val response: UserInfo?) : RecyclerView.Adapter<UserInfoViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        val binding = UserInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return UserInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd MMMM yyyy HH:mm:ss")
        with(holder)
        {
            with(binding)
            {
                if (response != null) {
                    val result = response.result[0]
                    binding.textViewRating.text = "Current Rating : " + result.rating.toString()
                    binding.textViewHnadle.text = result.handle
                    binding.textViewFriends.text = "Friends : "+result.friendOfCount.toString()
                    binding.textViewContribution.text = "Contribution : "+result.contribution.toString()
                    binding.textViewRegistrationTime.text = "Registered On : "+ sdf.format(Date(result.registrationTimeSeconds.toLong()*1000))
                    binding.textViewMaxRank.text = "Max Rank : "+ result.maxRank
                    binding.textViewRank.text = "Current Rank : "+result.rank
                    binding.textViewLastOnline.text = "Last Online : "+sdf.format(Date(result.lastOnlineTimeSeconds.toLong()*1000))
                    binding.textViewMaxRating.text = "Max Rating : "+result.maxRating.toString()

                    Glide.with(context).load(result.avatar).override(4000, 300)
                        .into(binding.imageViewAvtar)
                }
                else
                {
                    Toast.makeText(context,"Username Not Found",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

}
