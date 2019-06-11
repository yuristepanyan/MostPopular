package com.most.popular.arch.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.most.popular.R
import com.most.popular.arch.base.BaseActivity
import com.most.popular.model.ArticleDbModel
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

const val ARTICLE_ITEM = "articleItem"

class DetailActivity : BaseActivity() {
    @Inject
    lateinit var viewModel: DetailViewModel

    private lateinit var articleItem: ArticleDbModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setView(intent.getSerializableExtra(ARTICLE_ITEM) as ArticleDbModel)

        clicks()
        bind()
    }

    private fun setView(articleItem: ArticleDbModel) {
        this.articleItem = articleItem
        loadImage(articleItem.image)
        articleTitle.text = articleItem.title
        source.text = articleItem.source
        desc.text = articleItem.description
        date.text = articleItem.publishedDate
        quantity.text = when {
            articleItem.viewCount != null -> getString(R.string.views, articleItem.viewCount)
            articleItem.shareCount != null -> getString(R.string.shares, articleItem.shareCount)
            articleItem.emailCount != null -> getString(R.string.emails, articleItem.emailCount)
            else -> ""
        }
    }

    private fun clicks() {
        openInBrowser.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articleItem.url))
            startActivity(browserIntent)
        }
        favorite.setOnClickListener {
            (it.tag as ArticleDbModel?)?.let { dbModel ->
                viewModel.removeArticle.onNext(dbModel)
            } ?: viewModel.addArticleDbModel.onNext(articleItem)
        }
    }

    private fun bind() {
        viewModel.addedArticles.observe(this, Observer {
            favorite.setImageResource(
                if (it.contains(articleItem)) {
                    favorite.tag = articleItem
                    R.drawable.ic_favorite
                } else {
                    favorite.tag = null
                    R.drawable.ic_favorite_border
                }
            )
        })
    }

    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .apply(
                RequestOptions
                    .bitmapTransform(CenterCrop())
            )
            .into(image)
    }
}
