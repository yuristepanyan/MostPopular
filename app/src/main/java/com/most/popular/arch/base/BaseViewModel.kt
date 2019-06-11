package com.most.popular.arch.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.most.popular.db.ArticleDao
import com.most.popular.extensions.configureThread
import com.most.popular.model.Article
import com.most.popular.model.ArticleDbModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

open class BaseViewModel(
    private val articleDao: ArticleDao,
    private val disposable: CompositeDisposable
): ViewModel() {
    /** inputs */
    val addArticle = PublishSubject.create<Article>()
    val addArticleDbModel = PublishSubject.create<ArticleDbModel>()
    val removeArticle = PublishSubject.create<ArticleDbModel>()

    /** outputs */
    val addedArticles = MutableLiveData<List<ArticleDbModel>>()

    init {

        addArticle()

        removeArticle()

        getAddedArticles()
    }

    private fun addArticle() {
        addArticle.map { ArticleDbModel.valueOf(it) }
            .flatMapCompletable { articleDao.insertArticle(it).configureThread() }
            .subscribe()
            .addTo(disposable)
        addArticleDbModel.flatMapCompletable { articleDao.insertArticle(it).configureThread() }
            .subscribe()
            .addTo(disposable)
    }

    private fun removeArticle() {
        removeArticle.flatMapCompletable {
            articleDao.deleteArticle(it).configureThread()
        }
            .subscribe()
            .addTo(disposable)
    }

    private fun getAddedArticles() {
        articleDao.getArticles().configureThread()
            .subscribe { addedArticles.postValue(it) }
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}