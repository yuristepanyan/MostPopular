package com.most.popular.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles")
data class ArticleDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "url")
    var url: String = "",
    @ColumnInfo(name = "image")
    var image: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "publishedDate")
    var publishedDate: String = "",
    @ColumnInfo(name = "source")
    var source: String = "",
    @ColumnInfo(name = "shareCount")
    var shareCount: Int?,
    @ColumnInfo(name = "emailCount")
    var emailCount: Int?,
    @ColumnInfo(name = "viewCount")
    var viewCount: Int?
): Serializable {

    companion object {
        @JvmStatic
        fun valueOf(article: Article): ArticleDbModel {
            val images = article.media.firstOrNull { it.type == "image" }
            val image = images?.files?.first { it.format == "mediumThreeByTwo440" }?.url ?: ""
            return ArticleDbModel(
                article.id,
                article.title,
                article.url,
                image,
                article.abstract,
                article.publishedDate,
                article.source,
                article.shareCount,
                article.emailCount,
                article.views
            )
        }
    }
}