package com.most.popular.di.module

import androidx.lifecycle.ViewModelProviders
import com.most.popular.arch.favorites.FavoritesActivity
import com.most.popular.arch.favorites.FavoritesViewModel
import com.most.popular.arch.favorites.FavoritesViewModelFactory
import com.most.popular.db.ArticleDao
import com.most.popular.di.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FavoritesModule {
    @Provides
    @ActivityScope
    fun provideViewModel(activity: FavoritesActivity, articleDao: ArticleDao): FavoritesViewModel {
        return ViewModelProviders.of(activity, FavoritesViewModelFactory(articleDao, CompositeDisposable()))
            .get(FavoritesViewModel::class.java)
    }
}