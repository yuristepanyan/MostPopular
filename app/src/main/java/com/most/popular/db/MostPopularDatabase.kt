package com.most.popular.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.most.popular.model.ArticleDbModel

/**
 * The Room database that contains the articles table
 */
@Database(entities = [ArticleDbModel::class], version = 1, exportSchema = false)
abstract class MostPopularDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}