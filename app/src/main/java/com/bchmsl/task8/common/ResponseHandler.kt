package com.bchmsl.task8.common

import retrofit2.Response
import java.lang.Exception

interface ResponseHandler {

    suspend fun <T> safeCallApi(invoke: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = invoke()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(Throwable("Error Occurred!"))
                }
            } else {
                Resource.Error(Throwable(response.message()))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}