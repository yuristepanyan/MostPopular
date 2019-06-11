package com.most.popular.di.component

import com.most.popular.arch.detail.DetailActivity
import com.most.popular.di.ActivityScope
import com.most.popular.di.module.DetailModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    DetailModule::class
])
@ActivityScope
interface DetailComponent: AndroidInjector<DetailActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<DetailActivity>()
}