package com.example.technical.challenge.presentation.scheduleList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technical.challenge.databinding.AdapterRemedyItemRowBinding
import com.example.technical.challenge.domain.model.ScheduleModel
import com.example.technical.challenge.utils.getTimeAMPM

class RemedyItemAdapter(private val remedyList: List<ScheduleModel>) : RecyclerView.Adapter<RemedyItemAdapter.RemedyItemViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemedyItemViewHolder {
        val binding = AdapterRemedyItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RemedyItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RemedyItemViewHolder, position: Int) {
        with(holder){
            with(remedyList[position]){
                binding.txtTitle.text = this.alarmTime?.let { getTimeAMPM(it) }
                binding.txtMaker.text = this.instruction
                binding.txtName.text = this.note
            }
        }
    }

    override fun getItemCount(): Int = remedyList.size

    inner class RemedyItemViewHolder(val binding: AdapterRemedyItemRowBinding) : RecyclerView.ViewHolder(binding.root)

}