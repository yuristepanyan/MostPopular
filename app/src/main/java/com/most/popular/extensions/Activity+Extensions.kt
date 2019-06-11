package com.most.popular.extensions

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.openActivity(cls: Class<out Activity>) {
    startActivity(intentTo(cls))
}

fun AppCompatActivity.intentTo(cls: Class<out Activity>): Intent {
    return Intent(this, cls)
}