package com.bchmsl.task8.common.extensions

import com.bchmsl.task8.common.Resource

fun <T> Resource<T>.addOnSuccessListener(invoke: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) invoke(this.data)
    return this
}

fun <T> Resource<T>.addOnFailureListener(invoke: (Throwable) -> Unit): Resource<T> {
    if (this is Resource.Error) invoke(this.error)
    return this
}