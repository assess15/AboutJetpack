package com.laychv.arch_hilt.simple

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ApplicationComponent::class)
class SimpleModule {
    @Provides
//    singleton scoped 不能同时使用
//        @Singleton
    @ActivityScoped
    fun provideCustomTV(context: AppCompatActivity): View = TextView(context)
}