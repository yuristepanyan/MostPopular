package com.most.popular.arch.favorites

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import com.most.popular.R
import com.most.popular.arch.base.BaseActivity
import com.most.popular.arch.detail.ARTICLE_ITEM
import com.most.popular.arch.detail.DetailActivity
import com.most.popular.arch.favorites.adapter.FavoritesAdapter
import com.most.popular.extensions.intentTo
import com.most.popular.model.ArticleDbModel
import kotlinx.android.synthetic.main.activity_favorites.*
import javax.inject.Inject

class FavoritesActivity : BaseActivity(), FavoritesAdapter.FavoritesAdapterListener {
    @Inject
    lateinit var viewModel: FavoritesViewModel
    @Inject
    lateinit var adapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        initView()
    }

    private fun initView() {
        adapter.listener = this
        recycler.adapter = adapter
        bind()
    }

    private fun bind() {
        viewModel.addedArticles.observe(this, Observer {
            adapter.addedArticles = it
            if(it.isEmpty()) {
                noFavorites.visibility = VISIBLE
            } else {
                noFavorites.visibility = GONE
            }
        })
    }

    override fun removeItem(article: ArticleDbModel) {
        viewModel.removeArticle.onNext(article)
    }

    override fun itemClicked(article: ArticleDbModel) {
        startActivity(intentTo(DetailActivity::class.java).putExtra(ARTICLE_ITEM, article))
    }
}
