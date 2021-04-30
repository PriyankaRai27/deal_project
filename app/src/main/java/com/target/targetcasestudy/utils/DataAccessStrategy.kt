package com.target.targetcasestudy.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.target.targetcasestudy.utils.DealResource.Status.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> DealResource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<DealResource<T>> =
    liveData(Dispatchers.IO) {
        emit(DealResource.loading())
        val source = databaseQuery.invoke().map { DealResource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(DealResource.error(responseStatus.message!!))
            emitSource(source)
        }
    }