package com.laychv.ui_viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.laychv.ui_viewpager2.databinding.FragmentVpBinding

class ViewPager2Fragment : Fragment() {

    private lateinit var binding: FragmentVpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVpBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string = arguments?.getString("aa")
        binding.tvName.text = string
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