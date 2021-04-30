package com.target.targetcasestudy.data.remote

import javax.inject.Inject

class DealRemoteDataSource @Inject constructor(
    private val dealService: DealService
): BaseDataSource() {

    suspend fun getDeals() = getResult { dealService.getProducts() }
    suspend fun getDeals(id: Int) = getResult { dealService.getDealDetails(id) }
}