package br.com.madeiramadeira.eagleapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Marlon D. Rocha on 16/01/2019.
 */

internal object ApiRest {
    private val BASE_URL = "https://api.github.com/"
    fun initApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}