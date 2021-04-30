package com.target.targetcasestudy.di

import android.content.Context
import com.target.targetcasestudy.data.local.AppDatabase
import com.target.targetcasestudy.data.local.DealDao
import com.target.targetcasestudy.data.remote.DealRemoteDataSource
import com.target.targetcasestudy.data.remote.DealService
import com.target.targetcasestudy.data.repository.DealRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providedealService(retrofit: Retrofit): DealService = retrofit.create(DealService::class.java)

    @Singleton
    @Provides
    fun providedealRemoteDataSource(dealService: DealService) = DealRemoteDataSource(dealService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providedealDao(db: AppDatabase) = db.dealDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: DealRemoteDataSource,
                          localDataSource: DealDao) =
        DealRepository(remoteDataSource, localDataSource)
}