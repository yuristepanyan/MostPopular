package com.most.popular.di.module

import androidx.room.Room
import android.content.Context
import com.most.popular.BuildConfig
import com.most.popular.db.ArticleDao
import com.most.popular.db.MostPopularDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Provides
    @Singleton
    fun provideItemDao(db: MostPopularDatabase): ArticleDao {
        return db.articleDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        MostPopularDatabase::class.java,
        BuildConfig.DATABASE_NAME
    ).build()

}