package com.target.targetcasestudy.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.target.targetcasestudy.data.entities.DealItem

@Dao
interface DealDao {

    @Query("SELECT * FROM deal")
    fun getAllDeals() : LiveData<List<DealItem>>

    @Query("SELECT * FROM deal WHERE id = :id")
    fun getDealDetail(id: Int): LiveData<DealItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(deal: List<DealItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(deal: DealItem)


}