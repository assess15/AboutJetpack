package com.laychv.arch_paging3.paging3_package.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.laychv.arch_paging3.R
import com.laychv.arch_paging3.paging3_package.view.adapter.MainFooterAdapter

abstract class BasePagingVMFragment<M : Any, VM : BasePagingViewModel<M>, VH : RecyclerView.ViewHolder> :
    Fragment() {

    lateinit var inflate: View
    lateinit var mViewModel: VM
    private val mAdapter: PagingDataAdapter<M, VH> by lazy { getAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = View.inflate(activity, R.layout.fragment_base, null)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val mSwipeRefreshLayout = inflate.findViewById<SwipeRefreshLayout>(R.id.mSwipeRefreshLayout)
        mSwipeRefreshLayout.setOnRefreshListener {
            mAdapter.refresh()
        }
        val mRecyclerView = inflate.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.adapter =
            mAdapter.withLoadStateFooter(MainFooterAdapter { mAdapter.retry() })
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Loading -> mSwipeRefreshLayout.isRefreshing = true
                is LoadState.NotLoading -> mSwipeRefreshLayout.isRefreshing = false
                is LoadState.Error -> mSwipeRefreshLayout.isRefreshing = false
            }
        }

        mAdapter.addLoadStateListener {
            mSwipeRefreshLayout.isRefreshing = false
        }

        mViewModel = getViewModel() as VM

        mViewModel.pagedList.observe(viewLifecycleOwner, {
            mAdapter.submitData(lifecycle, it)
        })
    }

    abstract fun getAdapter(): PagingDataAdapter<M, VH>

    abstract fun getViewModel(): BaseViewModel

}