package com.bchmsl.task8.common

sealed class Resource<T>(val isLoading: Boolean = false) {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: Throwable) : Resource<T>()
    class Loading<T> : Resource<T>(true)
}