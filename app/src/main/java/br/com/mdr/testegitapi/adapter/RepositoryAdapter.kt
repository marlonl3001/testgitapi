package br.com.mdr.testegitapi.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.testegitapi.R
import br.com.mdr.testegitapi.databinding.RepositoryItemBinding
import br.com.mdr.testegitapi.interfaces.AdapterItemsContract
import br.com.mdr.testegitapi.model.LanguageColor
import br.com.mdr.testegitapi.model.Repository

/**
 * Created by Marlon D. Rocha on 22/04/2019.
 */
class RepositoryAdapter(var repositories: List<Repository>?, var context: Context): RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(),
    AdapterItemsContract {

    private var languageColors = ArrayList<LanguageColor>()

    init {
        setLanguageColors()
    }

    private fun setLanguageColors() {
        /*val jsonString: String
        try {
            val inputStream = context.assets.open("language_colors.json")
            val size = inputStream?.available()
            val buffer = ByteArray(size!!)
            with(inputStream, {
                read(buffer)
                close()
            })
            jsonString = String(buffer)
            val json: JsonObject = Parser().parse(jsonString) as JsonObject

            for (item in json.map)
                println("KEY: ${item.key} -- VALUE: ${item.value}")

        } catch(e: Exception) {
            e.printStackTrace()
        }*/
        //TODO: Criar rotina para ler as cores das linguagens
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun replaceItens(list: List<*>) {
        this.repositories = list as List<Repository>
        notifyDataSetChanged()
    }

    override fun getItemCount() = if (repositories != null) repositories!!.size else 0

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories!![position]
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
            val bundle = Bundle()
            bundle.putSerializable("repository", repository)
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
}