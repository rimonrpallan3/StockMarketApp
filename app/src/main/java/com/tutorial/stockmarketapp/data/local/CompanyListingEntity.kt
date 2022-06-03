package com.tutorial.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyListingEntity(
    val name: String,
    val symbols: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null,
)
