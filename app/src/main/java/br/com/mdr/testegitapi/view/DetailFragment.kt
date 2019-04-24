package br.com.mdr.testegitapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import br.com.mdr.testegitapi.App
import br.com.mdr.testegitapi.databinding.DetailFragmentBinding
import br.com.mdr.testegitapi.extensions.loadWith
import br.com.mdr.testegitapi.model.Repository
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        private lateinit var vm: DetailViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailFragmentBinding.inflate(inflater, container, false)
        vm = DetailViewModel()
        if (arguments != null) {
            val repository = arguments!!.getSerializable("repository") as Repository
            vm.repository.postValue(repository)
            App.activity!!.configureActionBar(repository.fullName)
        }

        addObservers(binding)
        return binding.root
    }

    private fun addObservers(binding: DetailFragmentBinding) {
        vm.repository.observe(viewLifecycleOwner, Observer { result ->
            binding.repository = result
            binding.hasLanguage = !result.homepage.isNullOrEmpty()
            binding.hasLanguage = !result.language.isNullOrEmpty()
            imgOwner.loadWith(result.owner.avatarUrl)
        })
    }
}
