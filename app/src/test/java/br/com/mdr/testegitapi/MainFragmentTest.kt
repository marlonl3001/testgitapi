package br.com.mdr.testegitapi.model

import br.com.mdr.testegitapi.extensions.convertDate
import br.com.mdr.testegitapi.extensions.sizePage
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */

@RunWith(JUnit4::class)
class MainFragmentTest {

    @Test
    fun checkExistLanguage() {
        val repository = Repository()
        repository.language = "Kotlin"
        assertTrue(repository.checklanguage())
    }

    @Test
    fun checkNotExistLanguage() {
        val repository = Repository()
        assertFalse(repository.checklanguage())
    }

    @Test
    fun checkExisHomePage() {
        val repository = Repository()
        repository.homepage = "www.teste.com.br"
        assertTrue(repository.checkHomepage())
    }
    @Test
    fun checkNotExisHomePage() {
        val repository = Repository()
        assertFalse(repository.checkHomepage())
    }

    @Test
    fun checkSizePage() {
        val repository = Repository()
        val list:MutableList<Repository> = ArrayList()
        for (i in 0..4){
            list.add(repository)
        }
        assertTrue(sizePage(list) > 0)
    }
    @Test
    fun dateValidate() {
        val data = "2009-09-29T18:41:28Z"
        assertTrue(convertDate(data) != "")
    }
    @Test
    fun dateNotValidate() {
        val data = "2009-09-29T18:41"
        assertTrue(convertDate(data) == "")
    }

}