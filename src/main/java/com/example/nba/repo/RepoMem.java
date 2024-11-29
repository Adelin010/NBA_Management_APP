package com.example.nba.repo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.nba.interfaces.*;

public class RepoMem<T extends IdBounded & FileBounded> implements Repo<T>{

    private Map<Integer,T> data = new TreeMap<>();

    @Override
    public void add(T obj) {
        data.putIfAbsent(obj.getId(), obj);
    }

    @Override
    public void update(T obj) {
        data.replace(obj.getId(), obj);
    }

    @Override
    public T get(Integer id) {
        return data.get(id);
    }

    @Override
    public void delete(Integer id) {
        data.remove(id);
    }

    @Override
    public List<T> getAll() {
        return data.values().stream().toList();
    }
    
}
