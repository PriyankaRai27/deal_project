package com.target.targetcasestudy.data.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class Price(
        @SerializedName("amount_in_cents")
        var amount_in_cents: Int,
        @SerializedName("currency_symbol")
        var currency_symbol: String,
        @SerializedName("display_string")
        var display_string: String
)