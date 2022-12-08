package br.com.mdr.gitrepositories.data.dao

import androidx.room.*
import br.com.mdr.base.domain.GitRepository

@Dao
interface RepositoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<GitRepository>)

    @Query("SELECT * FROM GitRepository")
    suspend fun findAll(): List<GitRepository>
}