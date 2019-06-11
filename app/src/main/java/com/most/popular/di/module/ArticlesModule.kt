package com.most.popular.di.module

import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.most.popular.arch.main.articleItems.ArticleItemsFragment
import com.most.popular.arch.main.articleItems.ArticleItemsViewModel
import com.most.popular.arch.main.articleItems.ArticleItemsViewModelFactory
import com.most.popular.db.ArticleDao
import com.most.popular.di.FragmentScope
import com.most.popular.net.MostPopularApi
import com.most.popular.util.ResponseErrorHandler
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ArticlesModule {

    @Provides
    @FragmentScope
    fun provideViewModel(
        fragment: ArticleItemsFragment,
        api: MostPopularApi,
        errorHandler: ResponseErrorHandler,
        articleDao: ArticleDao,
        gson: Gson
    ): ArticleItemsViewModel {
        return ViewModelProviders.of(
            fragment,
            ArticleItemsViewModelFactory(
                api,
                errorHandler,
                gson,
                articleDao,
                CompositeDisposable()
            )
        )
            .get(ArticleItemsViewModel::class.java)
    }
}