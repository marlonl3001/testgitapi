package br.com.mdr.gitrepositories

import br.com.mdr.gitrepositories.data.repository.MainRepository
import br.com.mdr.gitrepositories.di.useCaseModule
import br.com.mdr.gitrepositories.di.viewModelModule
import br.com.mdr.gitrepositories.presentation.viewmodel.MainViewModel
import br.com.mdr.test.base.BaseViewModelTest
import br.com.mdr.test.extension.test
import br.com.mdr.test.mocks.ExceptionTestData.ERROR_WRAPPER_422
import br.com.mdr.test.mocks.ExceptionTestData.HTTP_EXCEPTION_ERROR_422_DATA
import br.com.mdr.test.mocks.repositories
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@KoinApiExtension
@RunWith(JUnit4::class)
class MainViewModelTest: BaseViewModelTest() {
    private val viewModel: MainViewModel by inject()
    private val repository: MainRepository by inject()

    @Before
    fun setUp() {
        loadKoinModules(
    useCaseModule +
            viewModelModule +
            module(override = true) {
                single { mockk<MainRepository>() }
            }
        )
    }

    @Test
    fun givenSuccess_whenFetchRepositories_thenDisplayResult() {
        //given
        val observerSuccess = viewModel.repositories.test()
        val observerError = viewModel.apiError.test()
        coEvery { repository.getRepositories(false) } returns repositories

        //when
        viewModel.fetchRepositories()

        //then
        verify { observerSuccess.onChanged(repositories) }
        confirmVerified(observerSuccess, observerError)
    }

    @Test
    fun givenException_whenFetchRepositories_thenDisplayError() {
        //given
        val observerSuccess = viewModel.repositories.test()
        val observerError = viewModel.apiError.test()
        coEvery { repository.getRepositories(false) } throws HTTP_EXCEPTION_ERROR_422_DATA

        //when
        viewModel.fetchRepositories()

        //then
        verify { observerError.onChanged(ERROR_WRAPPER_422) }
        confirmVerified(observerSuccess, observerError)
    }
}