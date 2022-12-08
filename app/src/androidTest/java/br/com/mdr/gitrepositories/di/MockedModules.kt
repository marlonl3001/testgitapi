package br.com.mdr.gitrepositories.di

import br.com.mdr.gitrepositories.data.repository.MainRepository
import io.mockk.mockk
import org.koin.dsl.module

val mainMockedRepositoryModule = module(override = true) {
    single { mockk<MainRepository>(relaxed = true) }
}
