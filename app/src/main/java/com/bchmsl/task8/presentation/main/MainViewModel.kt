package com.bchmsl.task8.presentation.main

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.task8.common.extensions.addOnFailureListener
import com.bchmsl.task8.common.extensions.addOnSuccessListener
import com.bchmsl.task8.common.isNetworkAvailable
import com.bchmsl.task8.data.mapper.toItemEntity
import com.bchmsl.task8.data.mapper.toItemModel
import com.bchmsl.task8.domain.usecases.GetDataFromLocalUseCase
import com.bchmsl.task8.domain.usecases.GetDataFromRemoteUseCase
import com.bchmsl.task8.domain.usecases.SaveDataToLocalUseCase
import com.bchmsl.task8.presentation.model.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataFromLocalUseCase: GetDataFromLocalUseCase,
    private val getDataFromRemoteUseCase: GetDataFromRemoteUseCase,
    private val saveDataToLocalUseCase: SaveDataToLocalUseCase,
    private val app: Application
) : AndroidViewModel(app) {

    private val _successStateFlow = MutableStateFlow<List<ItemModel>?>(null)
    val successStateFlow get() = _successStateFlow.asStateFlow()

    private val _loadingStateFlow = MutableStateFlow<Boolean?>(null)
    val loadingStateFlow get() = _loadingStateFlow.asStateFlow()

    private val _errorStateFlow = MutableStateFlow<Throwable?>(null)
    val errorStateFlow get() = _errorStateFlow.asStateFlow()

    fun fetchData() {
        val isInternetConnectionAvailable = app.applicationContext.isNetworkAvailable()
        if (isInternetConnectionAvailable) {
            getItemsFromRemote()
        } else {
            getItemsFromLocal()
        }
    }

    private fun getItemsFromRemote() {
        viewModelScope.launch {
            getDataFromRemoteUseCase().collect { resource ->
                resource.addOnSuccessListener { successData ->
                    val successDataModels = successData.map { it.toItemModel() }
                    saveItemsToLocal(successDataModels)
                    _successStateFlow.value = successDataModels
                }.addOnFailureListener {
                    _errorStateFlow.value = it
                }
                _loadingStateFlow.value = resource.isLoading
            }
        }
    }

    private fun getItemsFromLocal() {
        viewModelScope.launch {
            getDataFromLocalUseCase().collect { data ->
                _successStateFlow.value = data.map { it.toItemModel() }
            }
        }
    }

    private fun saveItemsToLocal(items: List<ItemModel>) {
        viewModelScope.launch {
            saveDataToLocalUseCase(items.map { it.toItemEntity() })
        }
    }
}