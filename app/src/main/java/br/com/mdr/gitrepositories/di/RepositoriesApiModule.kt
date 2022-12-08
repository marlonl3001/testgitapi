package br.com.mdr.gitrepositories.di

import br.com.mdr.gitrepositories.data.RepositoriesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoriesApiModule = module {
    single { get<Retrofit>().create(RepositoriesApi::class.java) }
}