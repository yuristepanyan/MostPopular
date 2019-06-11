package com.most.popular.di.module

import androidx.lifecycle.ViewModelProviders
import com.most.popular.arch.detail.DetailActivity
import com.most.popular.arch.detail.DetailViewModel
import com.most.popular.arch.detail.DetailViewModelFactory
import com.most.popular.db.ArticleDao
import com.most.popular.di.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class DetailModule {
    @Provides
    @ActivityScope
    fun provideViewModel(activity: DetailActivity, articleDao: ArticleDao): DetailViewModel {
        return ViewModelProviders.of(activity, DetailViewModelFactory(articleDao, CompositeDisposable()))
            .get(DetailViewModel::class.java)
    }
}