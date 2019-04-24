package br.com.mdr.testegitapi.dao

import androidx.room.*
import br.com.mdr.testegitapi.model.Repository

/**
 * Created by Marlon D. Rocha on 23/04/2019.
 */
@Dao
interface RepositoryDAO {
    @Insert
    fun insert(items:List<Repository>)

    @Query("SELECT COUNT(*) FROM Repository")
    fun findQtd(): Int

    @Query("SELECT COUNT(*) FROM Repository where login = :sLogin ")
    fun findQtdRepository(sLogin: String): Int

    @Query("SELECT * FROM Repository where repository != 'S'")
    fun findAll(): MutableList<Repository>

    @Query("SELECT * FROM Repository where id = :idRepository")
    fun findItem(idRepository: Int):Repository

    @Query("SELECT * FROM Repository where login = :sLogin and repository = 'S'")
    fun findRepository(sLogin: String): MutableList<Repository>

    @Update
    fun update(item:Repository)

    @Query("DELETE FROM Repository")
    fun delete()
}