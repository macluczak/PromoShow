package com.example.api.shop.model

import com.example.api.product.model.Product
import kotlinx.serialization.Serializable
import java.util.Date
import java.util.UUID

@Serializable
data class Shop(
    val id: UUID?,
    val name: String,
    val description: String,
    val image: String,
    val locationCode: String,
    val products: List<Product>,
    val createdAt: Date?,
    val updatedAt: Date?
)