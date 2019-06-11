package com.most.popular.util

import retrofit2.Response
import java.net.UnknownHostException

interface ResponseErrorHandler {

    /**
     * Handles the request throwable case. If an [UnknownHostException] instance was thrown,
     * a "No internet connection" error is shown. Otherwise a [Throwable.getLocalizedMessage] error
     * message is shown. Both have the custom error code [NO_INTERNET_CODE].
     */
    fun <T> toResponseError(throwable: Throwable): Response<T>

}