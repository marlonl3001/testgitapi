package br.com.mdr.gitrepositories.presentation.fragment

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.base.domain.GitRepository
import br.com.mdr.gitrepositories.R
import br.com.mdr.gitrepositories.databinding.FragmentMainBinding
import br.com.mdr.gitrepositories.presentation.adapter.RepositoriesAdapter
import br.com.mdr.gitrepositories.presentation.viewmodel.MainViewModel
import br.com.mdr.gitrepositories.utils.SpacesItemDecoration
import br.com.mdr.gitrepositories.utils.extensions.navigateTo
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

const val IS_SORTING_KEY = "isSorting"

class MainFragment : Fragment() {
    private var isSorting = false
    private val viewModel: MainViewModel by sharedViewModel()

    private var binding: FragmentMainBinding? = null

    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSorting = savedInstanceState?.getBoolean(IS_SORTING_KEY) ?: isSorting
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        setupViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_SORTING_KEY, isSorting)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
        searchIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(),
            R.drawable.ic_search))
        searchIcon.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.white),
            android.graphics.PorterDuff.Mode.SRC_IN)

        searchView.queryHint = getString(R.string.search)
        searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text).setHintTextColor(
            ContextCompat.getColor(requireContext(), R.color.white)
        )
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                isSorting = true
                repositoriesAdapter.filterRepositories(newText)
                return true
            }
        })
        searchView.setOnCloseListener {
            isSorting = false
            isSorting
        }
    }

    private fun setupViewModel() {
        with(viewModel) {
            repositories.observe(viewLifecycleOwner) {
                repositoriesAdapter.fetchItems(it)
            }
        }
    }

    private fun setupRecyclerView() {
        repositoriesAdapter = RepositoriesAdapter(onRepositoryClick = onRepositoryClickListener())

        binding?.apply {
            recyclerRepositories.apply {
                addItemDecoration(SpacesItemDecoration(spanCount = resources.configuration.orientation,
                    orientation = GridLayoutManager.VERTICAL))
                addOnScrollListener(onScrollListener())
                adapter = repositoriesAdapter
            }

            (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        }
    }

    private fun onScrollListener() = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (recyclerView.canScrollVertically(RecyclerView.VERTICAL).not() && !isSorting) {
                viewModel.fetchRepositories(true)
            }
        }
    }

    private fun onRepositoryClickListener(): (movie: GitRepository?) -> Unit = {
        navigateTo(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
    }
}