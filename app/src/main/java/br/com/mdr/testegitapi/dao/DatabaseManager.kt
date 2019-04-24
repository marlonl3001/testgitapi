package br.com.mdr.testegitapi.dao

import androidx.room.Room
import br.com.mdr.testegitapi.App

object DatabaseManager {

    private var dbInstance: GitDatabase

    init {
        val appContext = App.context!!

        // Configura o Room
        dbInstance = Room.databaseBuilder(
                appContext,
                GitDatabase::class.java,
                "gitapi.sqlite")
                .build()
    }

    fun getRepositoryDAO(): RepositoryDAO {
        return dbInstance.repositoryDAO()
    }
}