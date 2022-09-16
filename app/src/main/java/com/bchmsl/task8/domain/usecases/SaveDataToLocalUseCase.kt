package com.bchmsl.task8.domain.usecases

import com.bchmsl.task8.data.local.ItemEntity
import com.bchmsl.task8.domain.repository.StoreRepository
import javax.inject.Inject

class SaveDataToLocalUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend operator fun invoke(items:List<ItemEntity>) = storeRepository.saveItemToLocal(items)
}