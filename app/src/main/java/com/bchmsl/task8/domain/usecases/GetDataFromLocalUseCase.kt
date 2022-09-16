package com.bchmsl.task8.domain.usecases

import com.bchmsl.task8.domain.repository.StoreRepository
import javax.inject.Inject

class GetDataFromLocalUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend operator fun invoke() = storeRepository.getItemsFromLocal()
}