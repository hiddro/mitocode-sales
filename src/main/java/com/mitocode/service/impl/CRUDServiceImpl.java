package com.mitocode.service.impl;

import com.mitocode.repository.GenericRepositories;
import com.mitocode.service.CRUDService;

import java.util.List;

public abstract class CRUDServiceImpl<T, ID> implements CRUDService<T, ID> {

    protected abstract GenericRepositories<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
