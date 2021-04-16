package de.imedia24.shop.domain.product

import de.imedia24.shop.db.entity.ProductEntity
import java.math.BigDecimal
import java.time.ZonedDateTime

data class ProductResponse(
    val sku: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val createdAt: ZonedDateTime?,
    val updatedAt: ZonedDateTime?,
    val stock: String
) {
    companion object {
        fun ProductEntity.toProductResponse() = ProductResponse(
            sku = sku,
            name = name,
            description = description ?: "",
            price = price,
            createdAt = createdAt,
            updatedAt = updatedAt,
            stock = stock?:  ""
        )
    }
}
