package com.assess15.ui_viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager2Adapter(
    activity: FragmentActivity,
    private val title: ArrayList<String>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return title.size
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPager2Fragment.getInstance(title[position])
    }
}