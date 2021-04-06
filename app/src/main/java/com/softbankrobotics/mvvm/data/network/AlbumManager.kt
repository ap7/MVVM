package com.softbankrobotics.mvvm.data.network

import com.softbankrobotics.mvvm.data.models.Album
import com.softbankrobotics.mvvm.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

// handle error
class AlbumManager(private val api: AlbumApi)  {
    suspend fun getAlbums(): List<Album> = api.getAlbums().responseOrError()
}

fun <T: Any> Response<T>.responseOrError() : T {
    if (isSuccessful) {
        return body()!!
    } else {
        val error = errorBody()?.string()
        val message = StringBuilder()
        error?.let {
            try {
                message.append(JSONObject(it).getString("message"))
            } catch(e: JSONException) {
                print(e.message)
            }
            message.append("\n")
        }
        message.append("Error Code: ${code()}")
        throw  ApiException(code().toString())
    }
}