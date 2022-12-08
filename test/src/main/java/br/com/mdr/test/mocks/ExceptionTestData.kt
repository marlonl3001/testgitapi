package br.com.mdr.test.mocks

import br.com.mdr.base.model.ApiError
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

private const val MESSAGE_ERROR = "Only the first 1000 search results are available"
private const val CONTENT_ERROR = "{\"message\":\"Only the first 1000 search results are available\"}"
private const val TEST_ERROR345 = "{\"message\":\"Only the first 1000 search results are available\",\"documentation_url\":\"https://docs.github.com/v3/search/\"}"
private const val TEXT_PLAIN = "text/plain"
private const val UNPROCESSABLE_ENTITY_ERROR_CODE = 422

object ExceptionTestData {

    val HTTP_EXCEPTION_ERROR_422_DATA = HttpException(
        Response.error<Any>(
            UNPROCESSABLE_ENTITY_ERROR_CODE,
            TEST_ERROR345.toResponseBody(TEXT_PLAIN.toMediaTypeOrNull())
        )
    )

        val ERROR_WRAPPER_422 = ApiError(MESSAGE_ERROR)

}
