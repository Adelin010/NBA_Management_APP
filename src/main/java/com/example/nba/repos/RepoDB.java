package com.example.nba.repos;

import java.util.List;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;

public class RepoDB<T extends Entity> implements Repo<T> {

    @Override
    public void add(T obj) {
        
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void update(T obj) {
        
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public T get(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
