package com.most.popular.model

data class Article(
    val id: Long,
    val title: String,
    val url: String,
    val abstract: String,
    val publishedDate: String,
    val source: String,
    val shareCount: Int?,
    val emailCount: Int?,
    val views: Int?,
    val media: List<Media>
)