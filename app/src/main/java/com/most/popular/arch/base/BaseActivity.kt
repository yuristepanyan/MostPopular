package com.most.popular.arch.base

import android.annotation.SuppressLint
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

@SuppressLint("Registered")
open class BaseActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}