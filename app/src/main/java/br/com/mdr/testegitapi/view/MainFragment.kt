package br.com.mdr.testegitapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.testegitapi.App
import br.com.mdr.testegitapi.R
import br.com.mdr.testegitapi.adapter.BindingAdapters
import br.com.mdr.testegitapi.adapter.RepositoryAdapter
import br.com.mdr.testegitapi.databinding.MainFragmentBinding
import br.com.mdr.testegitapi.model.Repository

import br.com.mdr.testegitapi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        private lateinit var vm: MainViewModel
        private lateinit var recyclerRepository: RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = MainViewModel()
        App.activity!!.mainViewModel = vm
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        vm.isLoading.postValue(true)
        addObservers(binding)
        return binding.root
    }

    private fun addObservers(binding: MainFragmentBinding) {
        val view = binding.root
        recyclerRepository = view.findViewById(R.id.recyclerRepositories)
        recyclerRepository.addOnScrollListener(vm.onScrollListener())

        val adapter = RepositoryAdapter(vm.repositories.value, context!!)

        val observer = object : Observer<MutableList<Repository>> {
            override fun onChanged(t: MutableList<Repository>?) {
                BindingAdapters.setItems(recyclerRepositories, t!!.toMutableList())
            }
        }
        vm.repositories.observe(viewLifecycleOwner, observer)

        binding.apply {
            recyclerRepository.adapter = adapter
            recyclerRepository.layoutManager = LinearLayoutManager(activity)
            recyclerRepository.setHasFixedSize(true)
        }

        vm.isLoading.observe(viewLifecycleOwner, Observer { result ->
            binding.isLoading = result
        })

    }

    override fun onStart() {
        super.onStart()
        vm.loadRepositories()

    }
}
