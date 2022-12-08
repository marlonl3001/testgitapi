package br.com.mdr.gitrepositories.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.base.domain.GitRepository
import br.com.mdr.gitrepositories.databinding.RepositoryItemBinding

private const val MIN_QUERY_CHAR_SIZE = 4

class RepositoriesAdapter(
    private val onRepositoryClick: (movie: GitRepository?) -> Unit
):
    RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {
    private var currentRepositoriesList = listOf<GitRepository>()
    private var filteredItems = mutableListOf<GitRepository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(inflater, parent, false)

        return RepositoriesViewHolder(binding, onRepositoryClick)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val repository =
            if (filteredItems.isEmpty())
                currentRepositoriesList[position]
            else
                filteredItems[position]

        holder.bind(repository)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun fetchItems(list: List<GitRepository>) {
        if (filteredItems.isEmpty())
            currentRepositoriesList = list

        notifyDataSetChanged()
    }

    fun filterRepositories(query: String) {

        if (query.length >= MIN_QUERY_CHAR_SIZE) {
            filteredItems.clear()
            filteredItems.addAll(currentRepositoriesList.filter { repository ->
                        repository.fullName.contains(query, true) ||
                        repository.owner.login.contains(query, true)})
        } else
            filteredItems.clear()

        fetchItems(
            filteredItems.ifEmpty { currentRepositoriesList }
        )
    }

    class RepositoriesViewHolder(
        private val binding: RepositoryItemBinding,
        private val onRepositoryClick: (repository: GitRepository?) -> Unit):
        RecyclerView.ViewHolder(binding.root) {

        private fun onRepositoryClickListener(gitRepository: GitRepository?) = View.OnClickListener {
            onRepositoryClick.invoke(gitRepository)
        }

        fun bind(gitRepository: GitRepository?) {
            binding.apply {
                repository = gitRepository
                clickListener = onRepositoryClickListener(gitRepository)
            }
        }
    }

    override fun getItemCount(): Int =
        if (filteredItems.isEmpty())
            currentRepositoriesList.count()
        else
            filteredItems.size
}