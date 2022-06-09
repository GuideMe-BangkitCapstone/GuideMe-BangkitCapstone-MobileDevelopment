package com.capstone.guideme.utils

import android.view.View
import java.util.*

fun showLoading(isLoading: Boolean, view: View) {
    if (isLoading) view.visibility = View.VISIBLE
    else view.visibility = View.INVISIBLE
}

fun String.toSlug() = lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("+")
    .replace("-+".toRegex(), "+")

val EMAIL_REGEX by lazy { "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})" }
fun isEmailValid(email: String): Boolean {
    return EMAIL_REGEX.toRegex().matches(email)
}
