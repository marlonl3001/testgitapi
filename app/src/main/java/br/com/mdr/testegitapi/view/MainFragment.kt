package br.com.mdr.testegitapi.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mdr.testegitapi.R
import br.com.mdr.testegitapi.adapter.RepositoryAdapter
import br.com.mdr.testegitapi.databinding.MainFragmentBinding
import br.com.mdr.testegitapi.model.Repository

import br.com.mdr.testegitapi.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        private lateinit var vm: MainViewModel
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = MainViewModel()
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
        val adapter = RepositoryAdapter(vm.repositories.value!!)

        val observer = Observer<MutableList<Repository>> {

        }

        binding.apply {
            recyclerRepositories.adapter = adapter
            recyclerRepositories.layoutManager = LinearLayoutManager(activity)
        }

        vm.isLoading.observe(viewLifecycleOwner, Observer { result ->
            binding.isLoading = result
        })
    }

    override fun onStart() {
        super.onStart()
        vm.load()
    }
}
