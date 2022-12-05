package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.ProductDTO;
import com.mitocode.model.entity.Product;
import com.mitocode.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    @Qualifier("productMapper")
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> list = productService.findAll()
                                            .stream()
                                            .map(product -> modelMapper.map(product, ProductDTO.class))
                                            .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ProductDTO product = modelMapper.map(productService.findById(id), ProductDTO.class);

        if(product == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO product) throws Exception{
        Product prd = productService.save(modelMapper.map(product, Product.class));

        return new ResponseEntity<>(modelMapper.map(prd, ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO product) throws Exception{
        Product prd = productService.update(modelMapper.map(product, Product.class));

        return new ResponseEntity<>(modelMapper.map(prd, ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Product prd = productService.findById(id);

        if(prd == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API http://localhost:8080/v3/api-docs / http://localhost:8080/swagger-ui.html
}
