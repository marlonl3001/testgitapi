package br.com.mdr.gitrepositories.di

import br.com.mdr.gitrepositories.data.repository.MainRepository
import br.com.mdr.gitrepositories.data.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get(), get()) }
}