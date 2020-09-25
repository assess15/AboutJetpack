package com.vaulert.arch_hilt.withoutNetwork

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class OutModel {

    @ActivityHash
    @Provides
    fun provideHash(): String {
        return hashCode().toString()
    }

    @OfferString
    @Provides
    fun provideString(): String {
        return "看到String没? 这是我注入的"
    }
}