package com.most.popular.db

import androidx.room.*
import com.most.popular.model.ArticleDbModel
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Data access table for the Articles table.
 *
 * The Articles table contains all Articles which are set as favorite
 */
@Dao
interface ArticleDao {

    /**
     * @return all Articles from the db
     */
    @Query("SELECT * FROM articles ORDER BY publishedDate DESC")
    fun getArticles(): Flowable<List<ArticleDbModel>>

    /**
     * Insert Article

     * @param article the Article to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: ArticleDbModel): Completable

    /**
     * delete Article from the database.
     * @param article to delete
     */
    @Delete
    fun deleteArticle(article: ArticleDbModel): Completable
}