package com.mitocode.service.impl;

import com.mitocode.model.entity.Provider;
import com.mitocode.repository.ProviderRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends  CRUDServiceImpl<Provider, Integer> implements ProviderService {

    @Autowired
    private ProviderRepositories providerRepositories;

    @Override
    protected GenericRepositories<Provider, Integer> getRepo() {
        return providerRepositories;
    }
}
