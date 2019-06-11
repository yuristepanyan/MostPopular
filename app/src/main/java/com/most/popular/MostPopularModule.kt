package com.most.popular

import android.app.Application
import android.content.Context
import com.most.popular.di.component.ArticlesComponent
import com.most.popular.di.component.DetailComponent
import com.most.popular.di.component.FavoritesComponent
import com.most.popular.di.component.MainComponent
import com.most.popular.util.ResponseErrorHandler
import com.most.popular.util.ResponseErrorHandlerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    subcomponents = [
        ArticlesComponent::class,
        MainComponent::class,
        FavoritesComponent::class,
        DetailComponent::class
    ]
)
abstract class MostPopularModule {
    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context

    @Binds
    @Singleton
    abstract fun bindErrorHandler(errorHandler: ResponseErrorHandlerImpl): ResponseErrorHandler
}