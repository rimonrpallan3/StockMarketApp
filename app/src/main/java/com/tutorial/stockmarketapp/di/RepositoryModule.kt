package com.tutorial.stockmarketapp.di

import com.tutorial.stockmarketapp.data.csv.CSVParser
import com.tutorial.stockmarketapp.data.csv.CompanyListingParser
import com.tutorial.stockmarketapp.data.repository.StockRepositoryImpl
import com.tutorial.stockmarketapp.domain.model.CompanyListing
import com.tutorial.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStocksRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}
