package br.com.mdr.gitrepositories.presentation

import br.com.mdr.gitrepositories.data.repository.MainRepository
import br.com.mdr.gitrepositories.domain.usecase.MainUseCase
import br.com.mdr.gitrepositories.presentation.viewmodel.MainViewModel
import br.com.mdr.test.rules.KoinRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RunWith(JUnit4::class)
class MainFragmentTest {

    @get:Rule
    val koiRule = KoinRule(
        listOf(
            module(override = true) {
                single { mockk<MainRepository>() }
                single { MainUseCase(get()) }
                viewModel { MainViewModel(get()) }
            }
        ),
        false
    )

    @Test
    fun givenRepositoryList_whenFetchListFromRepository_thenScrollToKoin() {
        withMainFragment {
            mockFirstPageRepositoriesList()
            launch()
        } actions {
            scrollToAlibaba()
        } verify {
            checkIsAlibabaDisplayed()
        }
    }

    @Test
    fun givenRepositoryList_whenFetchListFromRepository_thenLoadOkHttpRepository() {
        withMainFragment {
            mockFirstPageRepositoriesList()
            launch()
        } actions {
            clickAtOkHttp()
        }
    }
}