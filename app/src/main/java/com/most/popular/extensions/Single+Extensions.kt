package com.most.popular.extensions

import com.most.popular.util.ResponseErrorHandler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Configures the thread and handles the errors
 */
fun <T> Single<Response<T>>.toResponse(errorHandler: ResponseErrorHandler): Single<Response<T>> {
    return this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                errorHandler.toResponseError(it)
            }
}