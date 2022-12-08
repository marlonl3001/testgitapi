package br.com.mdr.base.extension

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.convertDate(): String? {
    return try {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val patternBr = "dd-MM-yyyy"
        val date = SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
        val brFormatter = SimpleDateFormat(patternBr, Locale.getDefault())
        return brFormatter.format(date ?: Date())
    } catch (e: Exception) {
        ""
    }
}