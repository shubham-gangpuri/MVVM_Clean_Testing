package com.example.technical.challenge.utils

import android.view.View

open class OnItemClickListener(val clickListener: (view: View, pos: Int) -> Unit) {
    fun onItemClickListener(view: View, pos: Int) = clickListener(view, pos)
}