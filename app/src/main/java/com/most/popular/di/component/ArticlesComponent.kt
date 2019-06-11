package com.most.popular.di.component

import com.most.popular.arch.main.articleItems.ArticleItemsFragment
import com.most.popular.di.FragmentScope
import com.most.popular.di.module.ArticlesModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(
    modules = [
        ArticlesModule::class
    ]
)
@FragmentScope
interface ArticlesComponent: AndroidInjector<ArticleItemsFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<ArticleItemsFragment>()
}