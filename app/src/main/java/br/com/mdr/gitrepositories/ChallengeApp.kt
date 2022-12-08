package br.com.mdr.gitrepositories

import android.app.Application
import br.com.mdr.gitrepositories.di.databaseModule
import br.com.mdr.gitrepositories.di.repositoriesApiModule
import br.com.mdr.gitrepositories.di.networkModule
import br.com.mdr.gitrepositories.di.repositoryModule
import br.com.mdr.gitrepositories.di.useCaseModule
import br.com.mdr.gitrepositories.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@ChallengeApp)
            modules(
                listOf(
                    databaseModule,
                    repositoriesApiModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}