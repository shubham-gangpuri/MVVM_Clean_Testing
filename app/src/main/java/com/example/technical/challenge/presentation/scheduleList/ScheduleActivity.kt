package com.example.technical.challenge.presentation.scheduleList

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.technical.challenge.R
import com.example.technical.challenge.databinding.ActivityScheduleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleActivity : FragmentActivity() {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityScheduleBinding

    private val scheduleSharedViewModel: ScheduleSharedViewModel by lazy {
        ViewModelProvider(this)[ScheduleSharedViewModel::class.java]
    }

    private val pagerAdapter: ScreenSlidePagerAdapter by lazy {
        ScreenSlidePagerAdapter(this, emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule)
        binding.viewModel = scheduleSharedViewModel
        viewPager = binding.pager
        viewPager.adapter = pagerAdapter
        init()
    }

    private fun init(){
        scheduleSharedViewModel.scheduleListResponse.observe(this, Observer {
            it?.keys?.let { set->
                pagerAdapter.scheduleDateList = set.toList() as List<String>
                pagerAdapter.notifyDataSetChanged()
            }
        })
        scheduleSharedViewModel.isChanged.observe(this, Observer {
            val current = viewPager.currentItem
            if(it == -1 && current != 0){
                viewPager.currentItem = current - 1
                scheduleSharedViewModel.isChanged.value = 0
            }else if(it == 1 && current != pagerAdapter.itemCount-1){
                viewPager.currentItem = current + 1
                scheduleSharedViewModel.isChanged.value = 0
            }
        })
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity, dates: List<String>) : FragmentStateAdapter(fa) {
        var scheduleDateList = dates
        override fun getItemCount(): Int = scheduleDateList.size

        override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment
            .newInstance(scheduleDateList[position])
    }
}