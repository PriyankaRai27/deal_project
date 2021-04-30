package com.target.targetcasestudy.data.remote

import com.target.targetcasestudy.utils.DealResource
import retrofit2.Response


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): DealResource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return DealResource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): DealResource<T> {
        return DealResource.error("Network call has failed for a following reason: $message")
    }

}