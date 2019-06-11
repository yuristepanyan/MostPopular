package com.most.popular.arch.main.articleItems

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

import com.most.popular.R
import com.most.popular.arch.detail.ARTICLE_ITEM
import com.most.popular.arch.detail.DetailActivity
import com.most.popular.arch.main.articleItems.adapter.ArticlesAdapter
import com.most.popular.model.Article
import com.most.popular.model.ArticleDbModel
import com.most.popular.model.RequestTypeEnum
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_article_items.*
import javax.inject.Inject

private const val TYPE_ARG = "type"

class ArticleItemsFragment : DaggerFragment(), ArticlesAdapter.ArticleAdapterListener {
    @Inject
    lateinit var viewModel: ArticleItemsViewModel
    @Inject
    lateinit var adapter: ArticlesAdapter

    private var type: Int? = null

    private var isRefreshed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt(TYPE_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        bind()
        getData()
        adapter.listener = this
        recycler.adapter = adapter
        swipeRefresh.setOnRefreshListener {
            isRefreshed = true
            getData()
        }
    }

    private fun getData() {
        viewModel.getArticles.onNext(
            when(type) {
                RequestTypeEnum.VIEWED.ordinal -> RequestTypeEnum.VIEWED.value
                RequestTypeEnum.EMAILED.ordinal -> RequestTypeEnum.EMAILED.value
                else -> RequestTypeEnum.SHARED.value
            }
        )
    }

    private fun bind() {
        viewModel.loading.observe(this, Observer {
            if (isRefreshed) {
                if(it == false) {
                    swipeRefresh.isRefreshing = false
                }
            } else {
                loading.visibility = if (it == true) VISIBLE else GONE
            }
        })
        viewModel.error.observe(this, Observer { Snackbar.make(loading, it ?: "", Snackbar.LENGTH_SHORT) })
        viewModel.articles.observe(this, Observer { adapter.articles = it ?: listOf() })
        viewModel.addedArticles.observe(this, Observer { adapter.addedArticles = it ?: listOf() })
    }

    override fun addItem(article: Article) {
        viewModel.addArticle.onNext(article)
    }

    override fun removeItem(article: ArticleDbModel) {
        viewModel.removeArticle.onNext(article)
    }

    override fun itemClicked(article: Article) {
        startActivity(Intent(activity, DetailActivity::class.java).putExtra(ARTICLE_ITEM, ArticleDbModel.valueOf(article)))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param type shows which type of articles must be got.
         * @return A new instance of fragment ArticleItemsFragment.
         */
        @JvmStatic
        fun newInstance(type: Int) =
            ArticleItemsFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE_ARG, type)
                }
            }
    }
}
