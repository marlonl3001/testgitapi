package br.com.mdr.testegitapi.extensions

import br.com.mdr.testegitapi.model.Repository
import java.text.SimpleDateFormat

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