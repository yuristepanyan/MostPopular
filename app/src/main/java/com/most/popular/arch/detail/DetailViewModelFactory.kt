package com.most.popular.arch.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.most.popular.db.ArticleDao
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
data class DetailViewModelFactory(
    private val articleDao: ArticleDao,
    private val disposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(articleDao, disposable) as T
    }
}