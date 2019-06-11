package com.most.popular.di.module

import com.most.popular.arch.main.MainActivity
import com.most.popular.arch.main.SectionsPagerAdapter
import com.most.popular.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @ActivityScope
    fun provideSectionsPagerAdapter(activity: MainActivity) =
        SectionsPagerAdapter(activity, activity.supportFragmentManager)
}