package com.example.technical.challenge.presentation.scheduleList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.FragmentScheduleBinding
import com.example.technical.challenge.domain.model.ScheduleModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenSlidePageFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentScheduleBinding

    private val scheduleSharedViewModel: ScheduleSharedViewModel by activityViewModels()
    private lateinit var remedyItemAdapter: RemedyItemAdapter

    companion object {
        fun newInstance(date : String): Fragment {
            val screenSlidePageFragment = ScreenSlidePageFragment()
            val arg = Bundle()
            arg.putString("date", date)
            screenSlidePageFragment.arguments = arg
            return screenSlidePageFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        binding.viewModel = scheduleSharedViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val date = bundle?.getString("date")
        binding.txtDate.text = date
        val scheduleList = scheduleSharedViewModel.scheduleListResponse.value?.get(date)
        remedyItemAdapter = RemedyItemAdapter(scheduleList as List<ScheduleModel>)
        binding.rcyViewRemedyList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcyViewRemedyList.adapter = remedyItemAdapter
        binding.left.setOnClickListener(this)
        binding.right.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.left){
            scheduleSharedViewModel.isChanged.value = -1
        }else{
            scheduleSharedViewModel.isChanged.value = 1
        }
    }
}