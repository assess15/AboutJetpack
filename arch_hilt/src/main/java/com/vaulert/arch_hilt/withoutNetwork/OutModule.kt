package com.vaulert.arch_hilt.withoutNetwork

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class OutModule {
    @AppHash
    @Provides
    fun provideHash(): String {
        return hashCode().toString()
    }
}
