package com.implemento.codeforces.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.implemento.codeforces.R
import com.implemento.codeforces.databinding.SubmissionInfoBinding
import com.implemento.codeforces.model.pojo.SubmissionInfo
import kotlinx.coroutines.withContext


class SubmissionInfoAdapter(private val response: SubmissionInfo?) :
    RecyclerView.Adapter<UserSubmissionViewHolder>() {

    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSubmissionViewHolder {
        context = parent.context
        return UserSubmissionViewHolder(SubmissionInfoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserSubmissionViewHolder, position: Int) {
        with(holder)
        {
            binding.apply {
                if (response != null) {
                    val result = response.result[position]
                    textViewContestId.text = result.contestId.toString()
                    textViewCreationTime.text = result.creationTimeSeconds.toString()
                    val problem = result.problem

                    textViewIndex.text = problem.index
                    textViewProblemName.text = problem.name
                    textViewPoints.text = "Points: " + problem.points.toString()
                    textViewProblemRating.text = "Rated: " + problem.rating.toString()

                    textViewLanguageUsed.text = result.programmingLanguage
                    textViewVerdict.text = result.verdict
                    textViewTimeConsumed.text = result.timeConsumedMillis.toString() + " millis"
                    textViewMemoryConsumed.text = result.memoryConsumedBytes.toString() + " Bytes"

                    val colorFail = ContextCompat.getColor(context,R.color.red)
                    val color = ContextCompat.getColor(context, R.color.teal_700)
                    if (result.verdict == "OK")
                        binding.root.setBackgroundColor(color)
                    else
                        binding.root.setBackgroundColor(colorFail)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if (response != null) {
            return response.result.size
        } else
            return 0;
    }

}
