package br.com.madeiramadeira.eagleapp.api

import br.com.mdr.testegitapi.model.GitResult
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by Marlon D. Rocha on 16/01/2019.
 */
object Api {
    private var adapter: Retrofit? = null
    private val api: ApiInterface
        get() {
            if (adapter == null)
                adapter = ApiRest.initApi()
            return adapter!!.create(ApiInterface::class.java)
        }

    fun getRepositories(query: String, perPage: String, page: String): Call<GitResult> = api.getRepositories(query,
        perPage, page)
}