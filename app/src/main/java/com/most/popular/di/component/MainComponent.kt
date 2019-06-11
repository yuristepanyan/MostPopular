package com.most.popular.di.component

import com.most.popular.arch.main.MainActivity
import com.most.popular.di.ActivityScope
import com.most.popular.di.module.MainModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    MainModule::class
])
@ActivityScope
interface MainComponent: AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>()
}