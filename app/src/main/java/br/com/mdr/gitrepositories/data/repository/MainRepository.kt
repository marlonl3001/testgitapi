package br.com.mdr.gitrepositories.data.repository

import br.com.mdr.base.domain.GitRepository
import br.com.mdr.gitrepositories.data.RepositoriesApi
import br.com.mdr.gitrepositories.data.dao.RepositoryDAO

const val QUERY_LIMIT = 25
private const val INITIAL_PAGE = 1

interface MainRepository {
    suspend fun getRepositories(loadFromApi: Boolean): List<GitRepository>?
}

class MainRepositoryImpl(
    private val api: RepositoriesApi,
    private val dao: RepositoryDAO
): MainRepository {
    private var actualPage: Int = INITIAL_PAGE

    override suspend fun getRepositories(loadFromApi: Boolean): List<GitRepository> {
        val reps = dao.findAll() as MutableList<GitRepository>
        val dbItemsPage: Int = reps.size / QUERY_LIMIT

        if (reps.isNotEmpty() && actualPage < dbItemsPage) {
            actualPage = dbItemsPage
        }

        if (loadFromApi || reps.isEmpty()) {
            val apiReps =
                api.getRepositories(QUERY_LIMIT.toString(), actualPage.toString())
                    .repositories

            reps.addAll(apiReps)

            dao.insert(reps)
            actualPage += INITIAL_PAGE
        }

        return reps
    }
}