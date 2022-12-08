package br.com.mdr.gitrepositories.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mdr.gitrepositories.databinding.ActivityMainBinding
import br.com.mdr.gitrepositories.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModels()
    }

    private fun setupViewModels() {
        with(viewModel) {
            loading.observe(this@MainActivity) {
                binding.loading = it
            }
            apiError.observe(this@MainActivity) {
                binding.error = it
            }
        }
    }
}