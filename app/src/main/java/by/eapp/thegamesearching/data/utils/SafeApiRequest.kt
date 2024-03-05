package by.eapp.thegamesearching.data.utils

import by.eapp.thegamesearching.data.remote.models.GameDetailDto
import com.google.android.gms.common.api.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {
    suspend fun <T : Any> safeApiRequest(call: () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val responseErr = response.errorBody()?.string()
            val message = StringBuilder()
            responseErr.let {
                try {
                    message.append(JSONObject(it).getString("error"))
                } catch (e: JSONException) {
                }
            }
            throw ApiException(message.toString())
        }
    }
}
class ApiException(message: String) : IOException(message)

