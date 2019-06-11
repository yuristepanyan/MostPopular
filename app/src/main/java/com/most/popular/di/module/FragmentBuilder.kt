package com.most.popular.di.module

import androidx.fragment.app.Fragment
import com.most.popular.arch.main.articleItems.ArticleItemsFragment
import com.most.popular.di.component.ArticlesComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(ArticleItemsFragment::class)
    abstract fun bindArticleItemsFragment(builder: ArticlesComponent.Builder): AndroidInjector.Factory<out Fragment>
}