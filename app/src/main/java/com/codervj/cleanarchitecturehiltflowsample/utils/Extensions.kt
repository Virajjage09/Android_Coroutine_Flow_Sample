package com.codervj.cleanarchitecturehiltflowsample.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.showShortToast(message: String = "") {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}