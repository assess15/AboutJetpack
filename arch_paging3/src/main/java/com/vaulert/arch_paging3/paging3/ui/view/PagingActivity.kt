package com.vaulert.arch_paging3.paging3.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaulert.arch_paging3.R
import com.vaulert.arch_paging3.paging3.ui.adapter.PagingAdapter
import com.vaulert.arch_paging3.paging3.ui.adapter.PagingFooterAdapter
import com.vaulert.arch_paging3.paging3.ui.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest

class PagingActivity : AppCompatActivity() {

    private val vm: PagingViewModel by viewModels()
    private val adapter: PagingAdapter by lazy { PagingAdapter() }
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        initAdapter()
    }

    private fun initAdapter() {
        rvList.layoutManager = LinearLayoutManager(this)
        // nothing
//        rvList.adapter = adapter
        // set footer
        rvList.adapter = adapter.withLoadStateFooter(PagingFooterAdapter(adapter))
//        rvList.adapter =
//            adapter.withLoadStateHeaderAndFooter(header =, footer = PagingFooterAdapter(adapter))

        lifecycleScope.launchWhenCreated {
            vm.getArticleData().observe(this@PagingActivity, Observer {
                adapter.submitData(lifecycle, it)
            })
        }

        job?.cancel()
        //监听刷新状态当刷新完成之后关闭刷新
        job = lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {
                        swipeRefresh.isRefreshing = true
                    }
                    is LoadState.NotLoading -> {
                        swipeRefresh.isRefreshing = false
                    }
                    is LoadState.Error -> {
                        swipeRefresh.isRefreshing = false
                    }
                }
            }
        }

        adapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}