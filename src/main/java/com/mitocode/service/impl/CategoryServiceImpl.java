package com.mitocode.service.impl;

import com.mitocode.model.entity.Category;
import com.mitocode.repository.CategoryRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CRUDServiceImpl<Category, Integer> implements CategoryService {

    @Autowired
    private CategoryRepositories categoryRepositories;


    @Override
    protected GenericRepositories<Category, Integer> getRepo() {
        return categoryRepositories;
    }
}
