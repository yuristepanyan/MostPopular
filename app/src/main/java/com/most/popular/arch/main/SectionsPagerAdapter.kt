package com.most.popular.arch.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.most.popular.R
import com.most.popular.arch.main.articleItems.ArticleItemsFragment
import com.most.popular.model.RequestTypeEnum

private val TAB_TITLES = arrayOf(
    R.string.viewed,
    R.string.shared,
    R.string.emailed
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ArticleItemsFragment.newInstance(
            when(position) {
                0 -> RequestTypeEnum.VIEWED.ordinal
                1 -> RequestTypeEnum.SHARED.ordinal
                else -> RequestTypeEnum.EMAILED.ordinal
            }
        )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount() = 3
}