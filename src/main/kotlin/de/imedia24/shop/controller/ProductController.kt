package de.imedia24.shop.controller

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.dto.ProductDTO
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/products/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products")
    fun findProductsBySKUs(@RequestParam skus : List<String>) : ResponseEntity<List<ProductResponse?>> {

        skus.forEach(System.out::println);
        val products = this.productService.findProductsBySKUs(skus);
        return if(products.isEmpty()){
            ResponseEntity.notFound().build();
        }else{
            ResponseEntity.ok(products);
        }
    }

    @PostMapping("/addproduct", produces = ["application/json;charset=utf-8"])
    fun addProduct(@RequestBody productDTO : ProductDTO) : ResponseEntity<ProductResponse?>{

        val newProduct = this.productService.addProduct(productDTO);
        return if(newProduct == null){
            ResponseEntity.badRequest().build();
        }else {
            ResponseEntity.ok(newProduct);
        }
    }

    @PutMapping("/updateproduct/{sku}", produces = ["application/json;charset=utf-8"])
    fun updateProduct(@PathVariable("sku") sku: String, @RequestBody productDTO: ProductDTO): ProductResponse? {
        return this.productService.updateProduct(sku, productDTO);
    }


}
