package com.softbankrobotics.mvvm.data.repositories

import com.softbankrobotics.mvvm.util.ApiException
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend() -> Response<T>) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw  ApiException(response.code().toString())
        }
    }
}


