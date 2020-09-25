package com.vaulert.arch_paging3.paging3_room.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<BVM : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: BVM
}