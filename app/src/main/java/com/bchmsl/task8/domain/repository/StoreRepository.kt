package com.bchmsl.task8.domain.repository

import com.bchmsl.task8.common.Resource
import com.bchmsl.task8.data.local.ItemEntity
import com.bchmsl.task8.data.remote.ItemDto
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getItemsFromRemote(): Flow<Resource<List<ItemDto>>>
    suspend fun getItemsFromLocal(): Flow<List<ItemEntity>>
    suspend fun saveItemToLocal(items: List<ItemEntity>)
}