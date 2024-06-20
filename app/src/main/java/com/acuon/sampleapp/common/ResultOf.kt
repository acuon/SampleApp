package com.acuon.sampleapp.common

/**
 * sealed class for representing the result of an operation(API call, database operations, etc)
 * - Success: indicating success status
 * - Error: indicating failure status
 * - Loading: indicating loading status
 *
 * @param T data type(Custom Data class or any data types)
 * @property data the data to access after success
 * @property message error message if the operation fails
 */
//sealed class _ResultOf<out T> {
//    /**
//     * @param data data associated with the successful operation
//     */
//    data class Success<out R>(val value: R) : ResultOf<R>()
//
//    /**
//     * @param message error message associated with the failed operation
//     * @param data optional data associated with the failed operation
//     */
//    data class Error(val message: String? = null, val throwable: Throwable? = null) :
//        ResultOf<Nothing>()
//
//    /**
//     * @param data optional data associated with the ongoing operation
//     */
//    data class Loading(val loading: Boolean = false) : ResultOf<Nothing>()
//}

sealed class ResultOf<T>(
    val value: T? = null,
    val message: String? = null,
    throwable: Throwable? = null
) {
    /**
     * @param data data associated with the successful operation
     */
    class Success<T>(value: T) : ResultOf<T>(value)

    /**
     * @param message error message associated with the failed operation
     * @param data optional data associated with the failed operation
     */
    class Error<T>(message: String?, value: Throwable? = null) :
        ResultOf<T>(throwable = value, message = message)

    /**
     * @param data optional data associated with the ongoing operation
     */
    class Loading<T>(value: T? = null) : ResultOf<T>(value)
}
