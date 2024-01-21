package com.example.api.product.model

import kotlinx.serialization.*
import java.util.*

@Serializable
data class Product(
    val id: UUID? = null,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val amount: Int,
    val category: String,
    val discountPrice: Double? = null,
    val discountDate: String? = null, // Format ISO 8601
    val shopId: UUID? = null,
    val createdAt: String? = null, // Format ISO 8601
    val updatedAt: String? = null // Format ISO 8601
)