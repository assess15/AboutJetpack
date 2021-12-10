package com.laychv.arch_hilt.withoutNetwork

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.laychv.arch_hilt.R
import com.laychv.arch_hilt.databinding.ActivityOutBinding
import com.laychv.arch_hilt.withNetwork.ui.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutBinding
    private val viewModel by viewModels<OutViewModel>()

    @JvmField
    @ActivityHash
    @Inject
    var hash: String? = null

    // 注入
    @JvmField
    @OfferString
    @Inject
    var offerString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_out
        )
        binding.textView.text = hash
        binding.textView2.text = offerString
        Log.d("aaa", viewModel.getRepositoryString())
        Log.d("aaa", offerString!!)

        binding.btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}