package com.wahidabd.githubapps.core

import retrofit2.Response
import java.net.SocketTimeoutException

class SafeCall {

    suspend fun <T> enqueue(call: suspend () -> Response<T>): Resource<T> =
        try {
            val res = call()
            val body = res.body()
            val errorBody = res.errorBody()

             if (res.isSuccessful && body != null) {
                Resource.success(res.body())
            }else if (errorBody != null) {
                 Resource.error(errorBody.toString(), null)
             } else {
                 Resource.error("UNKNOWN ERROR", null)
             }

        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> Resource.error("TIME OUT ERROR", null)
                else -> Resource.error("UNKNOWN ERROR", null)
            }
        }

    suspend fun <T, U> enqueue(req: T, call: suspend (T) -> Response<U>): Resource<U> =
        try {
            val res = call(req)
            val body = res.body()
            val errorBody = res.errorBody()

            if (res.isSuccessful && body != null) {
                Resource.success(res.body())
            } else if (errorBody != null) {
                Resource.error(errorBody.toString(), null)
            } else {
                Resource.error("UNKNOWN ERROR", null)
            }

        } catch (e: Exception) {
            when (e) {
                is SocketTimeoutException -> Resource.error("TIME OUT ERROR", null)
                else -> Resource.error("UNKNOWN ERROR", null)
            }
        }
}