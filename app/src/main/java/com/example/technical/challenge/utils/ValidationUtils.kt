package com.example.technical.challenge.utils

import android.content.Context
import com.example.technical.challenge.R
import java.util.*

fun validateSearchInput(
    context: Context,
    maker: String,
    model: String,
    year: String
): String? {
    if(maker.isEmpty() || model.isEmpty() || year.isEmpty()) {
        return context.getString(R.string.error_empty_field)
    }
    if(maker.length < 2 || maker.length > 40){
        return context.getString(R.string.error_maker_length)
    }
    if(model.length < 2 || model.length > 40){
        return context.getString(R.string.error_model_length)
    }
    if(Calendar.getInstance().get(Calendar.YEAR) < year.toInt()){
        return context.getString(R.string.error_year_selection)
    }
    return null
}