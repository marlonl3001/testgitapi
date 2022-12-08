package br.com.mdr.gitrepositories.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mdr.base.domain.GitRepository

@Database(entities = [GitRepository::class], version = 1, exportSchema = false)
abstract class GitDatabase : RoomDatabase() {

    abstract fun repositoryDAO(): RepositoryDAO

}
