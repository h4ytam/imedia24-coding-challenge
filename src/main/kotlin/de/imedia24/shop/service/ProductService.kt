package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import de.imedia24.shop.dto.ProductDTO
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import java.time.ZonedDateTime
import java.util.stream.Collectors

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        return  this.productRepository.findBySku(sku)?.toProductResponse();
    }

    fun findProductsBySKUs(skus : List<String>): List<ProductResponse?> {

        val produts = skus.stream()
            .map { sku -> this.findProductBySku(sku)}
            .filter{prod -> prod != null}
            .collect(Collectors.toList());
        return produts;
    }

    fun addProduct( productDTO: ProductDTO): ProductResponse? {
        return this.productRepository.save(productDTO.toEntity()).toProductResponse();
    }

    fun updateProduct(sku: String, productDTO: ProductDTO): ProductResponse? {
        var product = this.productRepository.findById(sku).get();
        product.name = productDTO.name;
        product.description = productDTO.description;
        product.price = productDTO.price;
        product.stock = productDTO.stock;
        product.updatedAt = ZonedDateTime.now();

        return this.productRepository.save(product).toProductResponse();

    }

}
