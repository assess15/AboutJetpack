package com.assess15.ui_viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_vp.*

class ViewPager2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_vp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string = arguments?.getString("aa")
        tvName.text = string
    }

    companion object {
        fun getInstance(s: String): ViewPager2Fragment {
            val data = Bundle().apply {
                putString("aa", s)
            }
            return ViewPager2Fragment().apply { arguments = data }
        }
    }

}