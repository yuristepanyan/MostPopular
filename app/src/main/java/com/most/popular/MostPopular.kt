package com.most.popular

import com.most.popular.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MostPopular : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerMostPopularComponent.builder()
            .application(this)
            .networkModule(NetworkModule())
            .build()
        component.inject(this)
        return component
    }

    companion object {
        lateinit var component: MostPopularComponent
    }
}