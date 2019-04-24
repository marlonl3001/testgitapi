package br.com.mdr.testegitapi.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.madeiramadeira.eagleapp.api.Api
import br.com.mdr.testegitapi.App
import br.com.mdr.testegitapi.dao.DatabaseManager
import br.com.mdr.testegitapi.dao.RepositoryDAO
import br.com.mdr.testegitapi.extensions.sizePage
import br.com.mdr.testegitapi.model.GitResult
import br.com.mdr.testegitapi.model.Repository
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainViewModel : ViewModel() {
    var repositories = MutableLiveData<MutableList<Repository>>()
    var isLoading = MutableLiveData<Boolean>()
    lateinit var repositoryDao: RepositoryDAO
    private var actualPage = 1
    private val pageSize = "10"

    fun loadRepositories(filter: String = "") {
        doAsync {
            repositoryDao = DatabaseManager.getRepositoryDAO()
            val qtd = repositoryDao.findQtd()
            val hasRegister = qtd > 0
            if (hasRegister && filter.isEmpty() && actualPage == 1) {
                isLoading.postValue(false)
                val reps = repositoryDao.findAll()
                repositories.postValue(reps)
                actualPage = sizePage(repositories.value!!)
            } else {
                val queryFilter = if (filter.isEmpty()) "android" else filter
                Api.getRepositories(queryFilter, pageSize, actualPage.toString()).enqueue(object : Callback<GitResult> {
                    override fun onResponse(call: Call<GitResult>, response: Response<GitResult>) {
                        if (response.isSuccessful) {

                            val resp: MutableList<Repository>?

                            if (actualPage == 1)
                                resp = response.body()!!.repositories
                            else {
                                resp = repositories.value
                                resp!!.addAll(response.body()!!.repositories)
                            }
                            repositories.postValue(resp)
                            if (resp.size > 0)
                                doAsync {
                                    repositoryDao.delete()
                                    repositoryDao.insert(checkRepository(resp))
                                }

                            actualPage++
                        } else {
                            Toast.makeText(App.context, "Erro ao efetuar busca de repositórios.\nTente novamente!",
                                Toast.LENGTH_SHORT).show()
                        }
                        isLoading.postValue(false)
                    }

                    override fun onFailure(call: Call<GitResult>, t: Throwable) {
                        isLoading.postValue(false)
                    }
                })
            }
        }
    }

    fun onScrollListener(): RecyclerView.OnScrollListener =
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val qtdItens = repositories.value!!.size
                if (qtdItens == layoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    loadRepositories()
                }
            }
        }

    private fun checkRepository(list:MutableList<Repository>):MutableList<Repository>{
        val temp = ArrayList<Repository>()
        for (item in list){
            val value = repositoryDao.findItem(item.id)
            if (value == null){
                item.repository = ""
                temp.add(item)
            }
        }
        return temp

    }
}
