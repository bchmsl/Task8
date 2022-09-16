package com.bchmsl.task8.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ItemEntity::class],
    version = 1
)
abstract class StoreDatabase : RoomDatabase(){

    abstract val dao: StoreDao
}