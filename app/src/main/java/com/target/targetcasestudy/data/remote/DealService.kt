package com.target.targetcasestudy.data.remote

import com.target.targetcasestudy.data.entities.DealItem
import com.target.targetcasestudy.data.entities.DealList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DealService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("deals")
    suspend fun getProducts(
    ): Response<DealList>

    @GET("deals/{id}")
    suspend fun getDealDetails(@Path("id") id: Int): Response<DealItem>
}