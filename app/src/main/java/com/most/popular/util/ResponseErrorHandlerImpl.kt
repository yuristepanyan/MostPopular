package com.most.popular.util

import android.content.Context
import com.google.gson.Gson
import com.most.popular.R
import com.most.popular.model.ErrorModel
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Response.error
import java.net.UnknownHostException
import javax.inject.Inject

const val NO_INTERNET_CODE = 600

class ResponseErrorHandlerImpl @Inject constructor(private val gson: Gson,
                                                   private val context: Context) : ResponseErrorHandler {

    private val jsonMediaType = MediaType.parse("application/json")

    override fun <T> toResponseError(throwable: Throwable): Response<T> {
        when (throwable) {
            is UnknownHostException ->
                return error(
                    NO_INTERNET_CODE, ResponseBody.create(
                        jsonMediaType,
                        gson.toJson(ErrorModel(context.getString(R.string.no_internet)))
                    )
                )
        }
        return error(
            NO_INTERNET_CODE, ResponseBody.create(
                jsonMediaType,
                gson.toJson(ErrorModel(throwable.localizedMessage))
            )
        )
    }

}