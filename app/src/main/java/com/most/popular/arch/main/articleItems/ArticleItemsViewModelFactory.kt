package com.most.popular.arch.main.articleItems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.most.popular.db.ArticleDao
import com.most.popular.net.MostPopularApi
import com.most.popular.util.ResponseErrorHandler
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class ArticleItemsViewModelFactory(
    private val api: MostPopularApi,
    private val errorHandler: ResponseErrorHandler,
    private val gson: Gson,
    private val articleDao: ArticleDao,
    private val disposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleItemsViewModel(api, errorHandler, gson, articleDao, disposable) as T
    }
}