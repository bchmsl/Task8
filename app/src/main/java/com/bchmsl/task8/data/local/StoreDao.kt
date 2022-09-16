package com.bchmsl.task8.data.local

import android.content.ClipData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(
        companyItemEntities: List<ItemEntity>
    )

    @Query("DELETE FROM itementity")
    suspend fun clearCompanyListings()

    @Query("SELECT * FROM itementity")
    suspend fun getAllItems(): List<ItemEntity>
}