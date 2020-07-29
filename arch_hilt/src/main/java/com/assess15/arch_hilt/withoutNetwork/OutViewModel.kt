package com.assess15.arch_hilt.withoutNetwork

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class OutViewModel @ViewModelInject constructor(
    private val repository: SampleRepository,
    @Assisted private val savedState: SavedStateHandle
) : ViewModel() {
    fun getRepositoryString(): String = repository.toString()
}