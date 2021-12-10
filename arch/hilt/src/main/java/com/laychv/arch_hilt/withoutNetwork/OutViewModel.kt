package com.laychv.arch_hilt.withoutNetwork

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted

class OutViewModel constructor(
    private val repository: SampleRepository,
    @Assisted private val savedState: SavedStateHandle
) : ViewModel() {
    fun getRepositoryString(): String = repository.toString()
}