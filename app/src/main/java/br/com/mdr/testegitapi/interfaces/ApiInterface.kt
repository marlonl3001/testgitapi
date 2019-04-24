package br.com.madeiramadeira.eagleapp.api

import br.com.mdr.testegitapi.model.GitResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Marlon D. Rocha on 16/01/2019.
 */
interface ApiInterface {
    @GET("search/repositories")
    fun getRepositories(@Query("q") query: String, @Query("per_page") perPage: String,
                        @Query("page") page: String): Call<GitResult>


}