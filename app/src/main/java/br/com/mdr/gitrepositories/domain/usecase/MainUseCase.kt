package br.com.mdr.gitrepositories.domain.usecase

import br.com.mdr.base.domain.GitRepository
import br.com.mdr.gitrepositories.data.repository.MainRepository

class MainUseCase(
    private val repository: MainRepository
) {

    suspend fun getRepositories(loadFromApi: Boolean): List<GitRepository>? =
        repository.getRepositories(loadFromApi)

}