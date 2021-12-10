package com.laychv.ui_viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.laychv.ui_viewpager2.databinding.ActivityViewpager2Binding

class ViewPager2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityViewpager2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpager2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        val title = ArrayList<String>()
        title.add("A")
        title.add("B")
        title.add("C")

        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })

        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.viewPager.adapter = ViewPager2Adapter(this, title)

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = title[position]
        }.attach()
    }
}