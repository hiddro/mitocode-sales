package com.mitocode.service.impl;

import com.mitocode.model.entity.Sale;
import com.mitocode.repository.SaleRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl extends  CRUDServiceImpl<Sale, Integer> implements SaleService {

    @Autowired
    private SaleRepositories saleRepositories;

    @Override
    protected GenericRepositories<Sale, Integer> getRepo() {
        return saleRepositories;
    }
}
