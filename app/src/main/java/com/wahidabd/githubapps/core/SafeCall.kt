package com.wahidabd.githubapps.core

import retrofit2.Response
import java.net.SocketTimeoutException

class SafeCall {

    suspend fun <T> enqueue(call: suspend () -> Response<T>): Resource<T?> {
        return try {
            val res = call()
            val body = res.body()
            val errorBody = res.errorBody()

             if (res.isSuccessful && body != null) {
                Resource.success(res.body())
            } else if (errorBody != null) {
                Resource.error(errorBody.toString())
            } else {
                Resource.error("UNKNOWN ERROR")
            }

        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> Resource.error("TIME OUT ERROR", null)
                else -> Resource.error("UNKNOWN ERROR", null)
            }
        }
    }
}