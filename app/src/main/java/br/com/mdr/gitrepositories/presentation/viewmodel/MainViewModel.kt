package br.com.mdr.gitrepositories.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mdr.base.domain.GitRepository
import br.com.mdr.base.presentation.BaseViewModel
import br.com.mdr.gitrepositories.domain.usecase.MainUseCase

class MainViewModel(
    private val useCase: MainUseCase
): BaseViewModel() {

    private val _repositories = MutableLiveData<List<GitRepository>>()
    var repositories: LiveData<List<GitRepository>> = _repositories

    init {
        fetchRepositories()
    }

    fun fetchRepositories(loadFromApi: Boolean = false) {
        launch(
            block = {
                _repositories.postValue(useCase.getRepositories(loadFromApi))
            }
        )
    }
}