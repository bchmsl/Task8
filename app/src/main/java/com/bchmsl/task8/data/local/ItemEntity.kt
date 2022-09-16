package com.bchmsl.task8.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title: String?,
    val cover:String?,
    val price:String?,
    val liked:Boolean?
)
