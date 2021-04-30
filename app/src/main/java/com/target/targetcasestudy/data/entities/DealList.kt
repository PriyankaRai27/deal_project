package com.target.targetcasestudy.data.entities

import com.google.gson.annotations.SerializedName

data class DealList(
        @SerializedName("products")
        var products: List<DealItem>
)