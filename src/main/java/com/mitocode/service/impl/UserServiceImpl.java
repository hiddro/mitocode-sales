package com.mitocode.service.impl;

import com.mitocode.model.entity.User;
import com.mitocode.repository.UserRepositories;
import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends  CRUDServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Override
    protected GenericRepositories<User, Integer> getRepo() {
        return userRepositories;
    }
}
