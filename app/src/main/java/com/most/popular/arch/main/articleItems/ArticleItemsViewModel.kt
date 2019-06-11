package com.most.popular.arch.main.articleItems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.most.popular.BuildConfig
import com.most.popular.arch.base.BaseViewModel
import com.most.popular.db.ArticleDao
import com.most.popular.extensions.configureThread
import com.most.popular.extensions.toResponse
import com.most.popular.model.Article
import com.most.popular.model.ArticleDbModel
import com.most.popular.model.ErrorModel
import com.most.popular.net.MostPopularApi
import com.most.popular.util.NO_INTERNET_CODE
import com.most.popular.util.ResponseErrorHandler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

class ArticleItemsViewModel(
    private val api: MostPopularApi,
    private val errorHandler: ResponseErrorHandler,
    private val gson: Gson,
    articleDao: ArticleDao,
    private val disposable: CompositeDisposable
) : BaseViewModel(articleDao, disposable) {
    /** inputs */
    val getArticles = PublishSubject.create<String>()

    /** outputs */
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val articles = MutableLiveData<List<Article>>()

    init {
        getArticles()
    }

    private fun getArticles() {
        val request = getArticles.doOnNext { loading.postValue(true) }
            .flatMapSingle {
                api.getArticles(it, "30.json", BuildConfig.API_KEY).toResponse(errorHandler)
            }.doOnNext { loading.postValue(false) }
            .share()

        request.filter { it.code() == NO_INTERNET_CODE && it.errorBody() != null }
            .subscribe { error.postValue(gson.fromJson(it.errorBody()!!.string(), ErrorModel::class.java).message) }
            .addTo(disposable)

        request.filter { it.isSuccessful }
            .subscribe { articles.postValue(it.body()?.results) }
            .addTo(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}