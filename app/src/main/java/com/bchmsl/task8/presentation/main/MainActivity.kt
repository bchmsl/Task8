package com.bchmsl.task8.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bchmsl.task8.common.extensions.makeSnackbar
import com.bchmsl.task8.databinding.ActivityMainBinding
import com.bchmsl.task8.presentation.adapter.ItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val itemsAdapter by lazy { ItemsAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.rvItems.adapter = itemsAdapter
        viewModel.fetchData()
        observers()
        listeners()
    }

    private fun listeners() {
        itemsAdapter.favoriteClickCallback = { item, position ->
            item.liked?.let {
                item.liked = !it
            }
            itemsAdapter.notifyItemChanged(position)
        }
    }

    private fun observers() {
        lifecycleScope.launch {
            viewModel.apply {
                launch {
                    successStateFlow.collect { data ->
                        data?.let { itemsAdapter.submitList(data) }
                    }
                }
                launch {
                    errorStateFlow.collect { throwable ->
                        throwable?.let { binding.root.makeSnackbar(throwable.message, true) }
                    }
                }
                launch {
                    loadingStateFlow.collect { loadingState ->
                        loadingState?.let { binding.progressBar.isVisible = loadingState }
                    }
                }
            }
        }
    }
}