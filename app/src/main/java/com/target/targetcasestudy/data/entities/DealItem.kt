package com.target.targetcasestudy.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "deal")
 data class DealItem(
    @PrimaryKey
    val id: Int?=0,
    val title: String?="",
    val description: String?="",
    val aisle: String?="",
    val image_url: String?="",
    @Embedded
    val regular_price:Price
)

