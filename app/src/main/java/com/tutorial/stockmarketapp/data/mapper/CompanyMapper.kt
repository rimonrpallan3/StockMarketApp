package com.tutorial.stockmarketapp.data.mapper

import com.tutorial.stockmarketapp.data.local.CompanyListingEntity
import com.tutorial.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbols = symbols,
        exchange = exchange,
    )
}

fun CompanyListing.toCompanyListing(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbols = symbols,
        exchange = exchange,
    )
}
