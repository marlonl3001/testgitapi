package br.com.mdr.testegitapi

import android.app.Application
import android.content.Context


/**
 * Created by Marlon D. Rocha on 23/04/19.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: Context? = null
        var activity: MainActivity? = null
    }
}
