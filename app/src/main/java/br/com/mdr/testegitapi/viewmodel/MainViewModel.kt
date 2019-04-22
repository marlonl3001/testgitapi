package br.com.mdr.testegitapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import br.com.madeiramadeira.eagleapp.api.Api
import br.com.mdr.testegitapi.model.GitResult
import br.com.mdr.testegitapi.model.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    var repositories = MutableLiveData<MutableList<Repository>>()
    var repositoryFilter = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        loadReposotories()
    }

    fun loadReposotories() {
        val filter = if (repositoryFilter.value.isNullOrEmpty()) "android" else repositoryFilter.value
        Api.getRepositories(filter!!).enqueue(object : Callback<GitResult> {
            override fun onResponse(call: Call<GitResult>, response: Response<GitResult>) {
                if (response.isSuccessful) {
                    repositories.postValue(response.body()!!.repositories)
                    isLoading.postValue(false)
                } else {
                    //TODO: Criar rotina para mostrar erro na consulta
                }
            }

            override fun onFailure(call: Call<GitResult>, t: Throwable) {
                isLoading.postValue(false)
            }
        })
    }
}
