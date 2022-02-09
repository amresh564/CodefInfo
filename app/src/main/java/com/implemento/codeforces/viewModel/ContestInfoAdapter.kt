package com.implemento.codeforces.viewModel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.implemento.codeforces.databinding.ParticipatedContestsBinding
import com.implemento.codeforces.model.pojo.ResultX

class ContestInfoAdapter(val response: List<ResultX>) : RecyclerView.Adapter<ContestInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestInfoViewHolder {
        val binding = ParticipatedContestsBinding.inflate(LayoutInflater.from(parent.context))
        return ContestInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestInfoViewHolder, position: Int) {
        with(holder)
        {
            binding.apply {
                val result = response[position]
                textViewContestNumber.text = (position+1).toString()
                textViewContestName.text = result.contestName
                textViewContestRank.text = "Rank : ${result.rank}"
            }
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }
}
