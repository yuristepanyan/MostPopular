package com.most.popular

import android.app.Application
import com.most.popular.di.module.ActivityBuilder
import com.most.popular.di.module.DatabaseModule
import com.most.popular.di.module.FragmentBuilder
import com.most.popular.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        MostPopularModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        FragmentBuilder::class,
        ActivityBuilder::class
    ]
)
interface MostPopularComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(module: NetworkModule): Builder

        fun build(): MostPopularComponent
    }
}