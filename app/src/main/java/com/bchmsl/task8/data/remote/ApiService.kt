package com.bchmsl.task8.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getItems(): Response<List<ItemDto>>
}