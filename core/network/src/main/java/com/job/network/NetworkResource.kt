package com.job.network

import retrofit2.Response

fun <T> Response<T>.getNetworkResource(): Resource<T> {
    return if (isSuccessful) {
        val body = body()
        if (body == null || code() == 204) {
            // Empty response 204
            Resource.Empty<T>(body())
        } else {
            Resource.Success<T>(body)
        }
    } else {
        Resource.Error<T>(
            message(),
            errorBody()?.string()
        )
    }
}
