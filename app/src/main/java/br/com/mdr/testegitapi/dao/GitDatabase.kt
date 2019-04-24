package br.com.mdr.testegitapi.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mdr.testegitapi.model.Repository

// Define as classes que precisam ser persistidas e a versão do banco
@Database(entities = [(Repository::class)], version = 1)

abstract class GitDatabase : RoomDatabase() {

    abstract fun repositoryDAO(): RepositoryDAO

}
