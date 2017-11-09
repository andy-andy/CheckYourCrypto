package com.at.checkyourcrypto.api.model

data class CryptoTicker(
        val id: String,
        val name: String,
        val price_usd: String,
        val symbol: String
)