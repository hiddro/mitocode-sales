package com.mitocode.service.impl;

import com.mitocode.model.entity.Role;
import com.mitocode.repository.RoleRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends  CRUDServiceImpl<Role, Integer> implements RoleService {

    @Autowired
    private RoleRepositories roleRepositories;

    @Override
    protected GenericRepositories<Role, Integer> getRepo() {
        return roleRepositories;
    }
}
