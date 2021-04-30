package com.target.targetcasestudy.data.repository

import com.target.targetcasestudy.data.local.DealDao
import com.target.targetcasestudy.data.remote.DealRemoteDataSource
import com.target.targetcasestudy.utils.performGetOperation
import javax.inject.Inject

class DealRepository @Inject constructor(
    private val remoteDataSource: DealRemoteDataSource,
    private val localDataSource: DealDao
) {

    fun getDealDetail(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getDealDetail(id) },
        networkCall = { remoteDataSource.getDeals(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getDeals() = performGetOperation(
        databaseQuery = { localDataSource.getAllDeals() },
        networkCall = { remoteDataSource.getDeals() },
        saveCallResult = { localDataSource.insertAll(it.products) }
    )
}