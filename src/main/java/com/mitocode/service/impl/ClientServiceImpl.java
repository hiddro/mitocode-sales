package com.mitocode.service.impl;

import com.mitocode.model.entity.Client;
import com.mitocode.repository.ClientRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends  CRUDServiceImpl<Client, Integer> implements ClientService {

    @Autowired
    private ClientRepositories clientRepositories;

    @Override
    protected GenericRepositories<Client, Integer> getRepo() {
        return clientRepositories;
    }
}
