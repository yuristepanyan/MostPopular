package com.most.popular.arch.favorites

import com.most.popular.arch.base.BaseViewModel
import com.most.popular.db.ArticleDao
import io.reactivex.disposables.CompositeDisposable

class FavoritesViewModel(
    articleDao: ArticleDao,
    disposable: CompositeDisposable
): BaseViewModel(articleDao, disposable)