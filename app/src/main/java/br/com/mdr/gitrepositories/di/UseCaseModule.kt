package br.com.mdr.gitrepositories.di

import br.com.mdr.gitrepositories.domain.usecase.MainUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MainUseCase(get()) }
}