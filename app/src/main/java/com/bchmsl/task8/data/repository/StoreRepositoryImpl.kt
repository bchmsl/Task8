package com.bchmsl.task8.data.repository

import com.bchmsl.task8.common.Resource
import com.bchmsl.task8.common.ResponseHandler
import com.bchmsl.task8.data.local.ItemEntity
import com.bchmsl.task8.data.local.StoreDatabase
import com.bchmsl.task8.data.remote.ApiService
import com.bchmsl.task8.data.remote.ItemDto
import com.bchmsl.task8.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: StoreDatabase
): StoreRepository, ResponseHandler {
    override suspend fun getItemsFromRemote(): Flow<Resource<List<ItemDto>>> = flow {
        emit(Resource.Loading())
        emit(safeCallApi { apiService.getItems() })
    }

    override suspend fun getItemsFromLocal(): Flow<List<ItemEntity>> = flow {
        emit(database.dao.getAllItems())
    }

    override suspend fun saveItemToLocal(items: List<ItemEntity>) {
        database.dao.clearCompanyListings()
        database.dao.insertItems(items)
    }
}