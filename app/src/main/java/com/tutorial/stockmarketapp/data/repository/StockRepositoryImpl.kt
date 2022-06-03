package com.tutorial.stockmarketapp.data.repository

import com.tutorial.stockmarketapp.data.local.StockDataBase
import com.tutorial.stockmarketapp.data.mapper.toCompanyListing
import com.tutorial.stockmarketapp.data.remote.StockApi
import com.tutorial.stockmarketapp.domain.model.CompanyListing
import com.tutorial.stockmarketapp.domain.repository.StockRepository
import com.tutorial.stockmarketapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDataBase,
) : StockRepository {

    private val dao = db.dao
    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListing.map {
                    it.toCompanyListing()
                }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListing()
                response.byteStream()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }
}
