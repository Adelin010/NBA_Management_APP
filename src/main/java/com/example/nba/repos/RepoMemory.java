package com.example.nba.repos;

import java.util.HashMap;
import java.util.List;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;


public class RepoMemory<T extends Entity> implements Repo<T>{
    /** Memory container where to save */ 
    private final HashMap<Integer, T> data;
    public RepoMemory(){
        data = new HashMap<>();
        /*
         * Connection to the DB created to instantiate the information in RAM
         */
    }
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
