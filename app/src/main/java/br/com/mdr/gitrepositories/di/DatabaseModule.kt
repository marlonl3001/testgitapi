package br.com.mdr.gitrepositories.di

import androidx.room.Room
import br.com.mdr.gitrepositories.data.dao.GitDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            GitDatabase::class.java,
            "repo_database"
        ).build()
    }

    single {
        val database = get<GitDatabase>()
        database.repositoryDAO()
    }
}