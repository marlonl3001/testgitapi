package br.com.mdr.testegitapi.extensions

import android.util.Log
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.mdr.testegitapi.model.Repository
import java.text.SimpleDateFormat

// Configura a Toolbar
fun AppCompatActivity.setupToolbar(@IdRes id: Int, title: String? = null, upNavigation: Boolean = false): ActionBar {
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if (title != null) {
        supportActionBar?.title = title
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    Log.d("carros", "Up nav config em $upNavigation $supportActionBar")
    return supportActionBar!!
}

fun sizePage(list:MutableList<Repository>):Int{
    return (list.size/20) + 1
}
fun convertDate(value:String):String{
    return try {
        val pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val patternBr = "dd-MM-yyyy"
        val date = SimpleDateFormat(pattern).parse(value)
        System.out.println(date)
        SimpleDateFormat(patternBr).format(date)
    }catch (e:Exception){
        ""
    }

}