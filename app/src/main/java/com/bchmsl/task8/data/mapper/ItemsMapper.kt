package com.bchmsl.task8.data.mapper

import com.bchmsl.task8.data.local.ItemEntity
import com.bchmsl.task8.data.remote.ItemDto
import com.bchmsl.task8.presentation.model.ItemModel

fun ItemDto.toItemModel() =
    ItemModel(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )

fun ItemModel.toItemDto() =
    ItemDto(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )

fun ItemModel.toItemEntity() =
    ItemEntity(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )

fun ItemEntity.toItemModel() =
    ItemModel(
        title = title,
        cover = cover,
        price = price,
        liked = liked
    )