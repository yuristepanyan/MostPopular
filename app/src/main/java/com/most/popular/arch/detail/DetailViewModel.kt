package com.most.popular.arch.detail

import com.most.popular.arch.base.BaseViewModel
import com.most.popular.db.ArticleDao
import io.reactivex.disposables.CompositeDisposable


class DetailViewModel(
    articleDao: ArticleDao,
    disposable: CompositeDisposable
): BaseViewModel(articleDao, disposable)