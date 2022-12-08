package br.com.mdr.gitrepositories.data

import br.com.mdr.base.data.entity.GitResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {

    @GET("search/repositories?sort=stars&q=language:kotlin")
    suspend fun getRepositories(@Query("per_page") perPage: String,
                                @Query("page") page: String): GitResponse
}
