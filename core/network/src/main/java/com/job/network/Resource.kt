package com.job.network

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(
        val errorBody: String,
        val message: String?
    ) : Resource<T>()

    class Empty<T>(val data: T?) : Resource<T>()
}
