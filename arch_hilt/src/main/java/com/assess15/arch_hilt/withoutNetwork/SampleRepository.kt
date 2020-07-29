package com.assess15.arch_hilt.withoutNetwork

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(@ApplicationContext context: Context) {

    fun getNetwork() {

    }
}