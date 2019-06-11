package com.most.popular.arch.favorites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.most.popular.R
import com.most.popular.model.ArticleDbModel
import kotlinx.android.synthetic.main.recycler_article_item.view.*
import javax.inject.Inject

class FavoritesAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var addedArticles = listOf<ArticleDbModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var listener: FavoritesAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_article_item, parent, false))
    }

    override fun getItemCount() = addedArticles.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(addedArticles[position])
    }

    interface FavoritesAdapterListener {
        fun removeItem(article: ArticleDbModel)

        fun itemClicked(article: ArticleDbModel)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.favorite.setOnClickListener {
                listener?.removeItem(addedArticles[adapterPosition])

            }

            view.setOnClickListener { listener?.itemClicked(addedArticles[adapterPosition]) }
        }

        fun bind(article: ArticleDbModel) {
            setMargin()

            view.title.text = article.title
            view.desc.text = article.description
            view.date.text = article.publishedDate

            val addedArticle = addedArticles.firstOrNull { it.id == article.id }

            view.favorite.setImageResource(
                if (addedArticle != null) {
                    view.favorite.tag = addedArticle
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_favorite_border
                }
            )
        }

        private fun setMargin() {
            (view.container.layoutParams as LinearLayout.LayoutParams).apply {
                bottomMargin = when (adapterPosition) {
                    itemCount - 1 -> context.resources.getDimensionPixelOffset(R.dimen.margin_standard)
                    else -> context.resources.getDimensionPixelOffset(R.dimen.margin_small)
                }
            }
        }
    }
}