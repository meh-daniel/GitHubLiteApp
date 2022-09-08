package meh.daniel.com.data


abstract class BaseRepository {
    /**
     * Do network request
     *
     * @param doSomethingInSuccess for working when request result is success
     * @return request result in [flow] with [Either]
     */
//    protected fun <T> doRequest(
//        doSomethingInSuccess: ((T) -> Unit)? = null,
//        request: suspend () -> T
//    ) = flow<Either<String, T>> {
//        request().also { data ->
//            doSomethingInSuccess?.invoke(data)
//            emit(Either.Right(value = data))
//        }
//    }.flowOn(Dispatchers.IO).catch { exception ->
//        emit(Either.Left(value = exception.localizedMessage ?: "Error Occurred!"))
//    }
}