package com.most.popular.di.module

import android.app.Activity
import com.most.popular.arch.detail.DetailActivity
import com.most.popular.arch.favorites.FavoritesActivity
import com.most.popular.arch.main.MainActivity
import com.most.popular.di.component.DetailComponent
import com.most.popular.di.component.FavoritesComponent
import com.most.popular.di.component.MainComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivity(builder: MainComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(FavoritesActivity::class)
    abstract fun bindFavoritesActivity(builder: FavoritesComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DetailActivity::class)
    abstract fun bindDetailActivity(builder: DetailComponent.Builder): AndroidInjector.Factory<out Activity>
}