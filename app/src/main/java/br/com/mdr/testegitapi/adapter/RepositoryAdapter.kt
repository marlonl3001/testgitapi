package br.com.mdr.testegitapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.testegitapi.databinding.RepositoryItemBinding
import br.com.mdr.testegitapi.extensions.loadWith
import br.com.mdr.testegitapi.interfaces.AdapterItemsContract
import br.com.mdr.testegitapi.model.Repository

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */
class RepositoryAdapter(var repositories: List<Repository>): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(),
    AdapterItemsContract {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun replaceItens(list: List<*>) {
        this.repositories = list as List<Repository>
        notifyDataSetChanged()
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bind(repository, onRepositoryClickListener(repository))
    }

    inner class RepositoryViewHolder(val binding: RepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository, listener: View.OnClickListener) {
            binding.repository = repository
            binding.clickListener = listener
        }
    }

    private fun onRepositoryClickListener(repository: Repository): View.OnClickListener =
        View.OnClickListener {
            //TODO: Implementar navegaçãopara tela de detalhe do repositório
        }
}