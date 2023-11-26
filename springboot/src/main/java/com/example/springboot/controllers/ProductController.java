package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador para manipulação de produtos.
 */
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    /**
     * Cria um novo produto.
     *
     * @param productRecordDto DTO contendo informações do produto.
     * @return ResponseEntity contendo o produto criado e status HTTP 201 (Created).
     */
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    /**
     * Obtém todos os produtos.
     *
     * @return ResponseEntity contendo a lista de produtos e status HTTP 200 (OK).
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        List<ProductModel> productsList = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    /**
     * Obtém um produto específico pelo ID.
     *
     * @param id ID do produto.
     * @return ResponseEntity contendo o produto encontrado ou mensagem de erro e status HTTP correspondente.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    /**
     * Atualiza um produto existente pelo ID.
     *
     * @param id ID do produto a ser atualizado.
     * @param productRecordDto DTO contendo as informações atualizadas do produto.
     * @return ResponseEntity contendo o produto atualizado e status HTTP correspondente.
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    /**
     * Exclui um produto pelo ID.
     *
     * @param id ID do produto a ser excluído.
     * @return ResponseEntity contendo mensagem de sucesso ou erro e status HTTP correspondente.
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }
}
