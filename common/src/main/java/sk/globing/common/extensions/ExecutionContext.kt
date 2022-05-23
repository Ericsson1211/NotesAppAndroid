package sk.globing.common.extensions

import retrofit2.HttpException

/**
 * Without exhaustive extension on when block, it will not force to add all branches
 */
val <T> T.exhaustive: T
    get() = this

sealed class ReturnResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ReturnResult<T>()

    data class Error<out T : Any>(val error: ErrorResult, val data: T? = null) : ReturnResult<T>()

    data class Running<out T : Any>(val data: T?) : ReturnResult<T>()

    data class ApiError<out T : Any>(val apiError: ApiErrorResult, val data: T? = null) :
        ReturnResult<T>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=${error.throwable}"
            is ApiError -> "ReturnApiResult[code=${apiError.code},message=${apiError.message},throwable=${apiError.throwable}]"
            is Running -> "Success[data=$data]"
        }
    }

    fun isSuccess() = this is Success

    fun isError() = this is Error || this is ApiError

    fun isRunning() = this is Running

    fun isApiError() = this is ApiError

    fun getOrNull() = when {
        this is Success -> data
        this is Error -> data
        this is ApiError -> data
        else -> null
    }
}

fun <T : Any> httpError(code: Int, message: String?, throwable: Throwable?, data: T?) =
    ReturnResult.ApiError(ApiErrorResult(code, message, throwable), data)

open class ErrorResult(open var message: String? = null, open var throwable: Throwable? = null)

open class ApiErrorResult(
    val code: Int,
    override var message: String?,
    override var throwable: Throwable? = null
) : ErrorResult()

suspend fun <T : Any> safeApiCall(
    call: suspend () -> ReturnResult<T>,
    errorMessage: String
): ReturnResult<T> {
    return try {
        call()
    } catch (throwable: Throwable) {
        if (throwable is HttpException) {
            httpError(throwable.code(), throwable.message(), throwable, throwable.response()?.body())
        }
        ReturnResult.Error(ErrorResult(errorMessage, throwable))
    }
}

