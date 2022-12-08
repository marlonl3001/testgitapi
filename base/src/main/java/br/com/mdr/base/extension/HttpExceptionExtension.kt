package br.com.mdr.base.extension

import br.com.mdr.base.extension.SerializationExtension.jsonToObject
import br.com.mdr.base.model.ApiError
import retrofit2.HttpException
import java.net.UnknownHostException

@SuppressWarnings("TooGenericExceptionCaught")
fun HttpException.toErrorWrapper(): ApiError? {
    return try {
        val string = this.response()?.errorBody()?.string()
        string?.jsonToObject<ApiError>()
    } catch (ex: Exception) {
        createDefaultError(ex.message)
    }
}

fun UnknownHostException.toUnknownErrorWrapper() = createDefaultError(this.message)

private fun createDefaultError(message: String?) =
    ApiError(message ?: "")
