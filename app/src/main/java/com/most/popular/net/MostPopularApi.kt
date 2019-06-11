package com.most.popular.net

import com.most.popular.model.ArticlesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MostPopularApi {
    @GET("{name}/{days}")
    fun getArticles(
        @Path("name") name: String,
        @Path("days") days: String,
        @Query("api-key") apiKey: String
    ): Single<Response<ArticlesResponse>>
}