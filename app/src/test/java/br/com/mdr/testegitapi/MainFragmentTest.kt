package br.com.mdr.testegitapi.model

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */

class MainFragmentTest {

    @Test
    fun checkExistLanguage() {
        val item:items = items()
        item.language = "Kotlin"
        assertTrue(item.checklanguage())
    }
    @Test
    fun checkNotExistLanguage() {
        val item:items = items()
        assertFalse(item.checklanguage())
    }

    @Test
    fun checkExisHomePage() {
        val item:items = items()
        item.homepage = "www.teste.com.br"
        assertTrue(item.checkHomepage())
    }
    @Test
    fun checkNotExisHomePage() {
        val item:items = items()
        assertFalse(item.checkHomepage())
    }

    @Test
    fun checkSizePage() {
        val item:items = items()
        val list:MutableList<Repository> = ArrayList<Repository>()
        for (i in 0..4){
            list.add(item)
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