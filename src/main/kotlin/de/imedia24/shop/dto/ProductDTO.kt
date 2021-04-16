package de.imedia24.shop.dto;

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.domain.product.ProductResponse
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.math.BigDecimal;
import java.time.ZonedDateTime

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ProductDTO (

    val sku: String ,

    val name: String,

    val description: String? = null,

    val price: BigDecimal,

    val createdAt: ZonedDateTime,

    val updatedAt: ZonedDateTime,

    val stock: String

) {
    fun toEntity(): ProductEntity {
        return ProductEntity(sku, name, description, price, createdAt, updatedAt, stock )
    }
}
