package com.assess15.arch_paging3.paging3_room.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.assess15.arch_paging3.R
import com.assess15.arch_paging3.paging3_room.base.BaseActivity
import com.assess15.arch_paging3.paging3_room.comm.InjectFactory
import com.assess15.arch_paging3.paging3_room.ui.adapter.PagingAdapter
import com.assess15.arch_paging3.paging3_room.ui.adapter.PagingFooterAdapter
import com.assess15.arch_paging3.paging3_room.ui.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest

class PagingRoomActivity : BaseActivity<PagingViewModel>() {

    private val adapter: PagingAdapter by lazy { PagingAdapter() }
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)

        initAdapter()
    }

    override val viewModel: PagingViewModel by viewModels {
        InjectFactory.provideViewModelFactory(this)
    }

    private fun initAdapter() {

        rvList.layoutManager = LinearLayoutManager(this)

        // set footer
        rvList.adapter = adapter.withLoadStateFooter(PagingFooterAdapter(adapter))

        lifecycleScope.launchWhenCreated {
            viewModel.getArticles(this@PagingRoomActivity, Observer {
                adapter.submitData(lifecycle, it)
            })
        }

        /**
        lifecycleScope.launchWhenCreated {
        //            viewModel.getArticle().observe(this@PagingRoomActivity, Observer {
        //                adapter.submitData(lifecycle, it)
        //            })
        viewModel.getArticles(this@PagingRoomActivity, Observer {
        adapter.submitData(lifecycle, it)
        })
        }**/

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