package com.capstone.guideme.utils

import android.view.View

fun showLoading(isLoading: Boolean, view: View) {
    if (isLoading) view.visibility = View.VISIBLE
    else view.visibility = View.INVISIBLE
}