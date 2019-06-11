package com.most.popular.di.component

import com.most.popular.arch.favorites.FavoritesActivity
import com.most.popular.di.ActivityScope
import com.most.popular.di.module.FavoritesModule
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = [
    FavoritesModule::class
])
@ActivityScope
interface FavoritesComponent: AndroidInjector<FavoritesActivity> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<FavoritesActivity>()
}