package com.tutorial.stockmarketapp.domain.repository

import com.tutorial.stockmarketapp.domain.model.CompanyListing
import com.tutorial.stockmarketapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>>
}
