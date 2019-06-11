package com.most.popular.arch.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.most.popular.db.ArticleDao
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
data class FavoritesViewModelFactory(
    private val articleDao: ArticleDao,
    private val disposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(articleDao, disposable) as T
    }
}