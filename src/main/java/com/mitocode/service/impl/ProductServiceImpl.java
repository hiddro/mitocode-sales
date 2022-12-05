package com.mitocode.service.impl;

import com.mitocode.model.entity.Product;
import com.mitocode.repository.ProductRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends  CRUDServiceImpl<Product, Integer> implements ProductService {

    @Autowired
    private ProductRepositories productRepositories;

    @Override
    protected GenericRepositories<Product, Integer> getRepo() {
        return productRepositories;
    }
}
