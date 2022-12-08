package br.com.mdr.gitrepositories.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mdr.gitrepositories.databinding.FragmentDetailBinding
import br.com.mdr.gitrepositories.utils.extensions.pop

class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding?.toolbar?.setNavigationOnClickListener {
            pop()
        }

        fetchRepositoryData()
    }

    private fun fetchRepositoryData() {
        arguments?.let { bundle ->
            binding?.apply {
                val repositoryObject = DetailFragmentArgs.fromBundle(bundle).repository
                repository = repositoryObject
                hasSite = repositoryObject?.homepage.isNullOrEmpty().not()
            }
        }
    }

}