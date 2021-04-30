package com.target.targetcasestudy.utils

data class DealResource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): DealResource<T> {
            return DealResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): DealResource<T> {
            return DealResource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): DealResource<T> {
            return DealResource(Status.LOADING, data, null)
        }
    }
}