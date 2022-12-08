package br.com.mdr.gitrepositories.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import br.com.mdr.gitrepositories.R
import br.com.mdr.gitrepositories.data.repository.MainRepository
import br.com.mdr.gitrepositories.presentation.adapter.RepositoriesAdapter
import br.com.mdr.gitrepositories.presentation.fragment.MainFragment
import br.com.mdr.test.extension.atPosition
import br.com.mdr.test.extension.recyclerViewClickItem
import br.com.mdr.test.extension.scrollToPositionRecyclerView
import br.com.mdr.test.mocks.repositories
import io.mockk.coEvery
import io.mockk.mockk
import org.koin.test.KoinTest
import org.koin.test.inject

fun MainFragmentTest.withMainFragment(func: MainFragmentRobot.() -> Unit) =
    MainFragmentRobot().apply(func)

class MainFragmentRobot: KoinTest {
    private val mockNavigation = mockk<NavController>(relaxed = true)
    private val repository: MainRepository by inject()

    infix fun actions(func: MainFragmentRobot.() -> Unit) = this.apply(func)
    infix fun verify(func: MainFragmentResult.() -> Unit) =
        MainFragmentResult().apply(func)

    fun launch() {
        launchFragmentInContainer<MainFragment>(
            themeResId = R.style.Theme_GitRepositories
        ).withFragment {
            Navigation.setViewNavController(requireView(), mockNavigation)
        }
    }

    fun mockFirstPageRepositoriesList() {
        coEvery { repository.getRepositories(false) } returns repositories
    }

    fun scrollToAlibaba() =
        R.id.recyclerRepositories
            .scrollToPositionRecyclerView<RepositoriesAdapter.RepositoriesViewHolder>(4)

    fun clickAtOkHttp() =
        R.id.recyclerRepositories
            .recyclerViewClickItem<RepositoriesAdapter.RepositoriesViewHolder>(1)

}

class MainFragmentResult {
    fun checkIsAlibabaDisplayed() {
        R.id.recyclerRepositories.atPosition(4, "alibaba/p3c")
    }

}