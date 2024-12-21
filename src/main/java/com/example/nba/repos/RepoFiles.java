package com.example.nba.repos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;
import com.example.nba.util.FileHandlerUtil;

public class RepoFiles<T extends Entity> implements Repo<T> {

    private final FileHandlerUtil<T> fh;
    private final Class<T> type;   
    private final String fileName;

    public Class<T> getType(){
        return type;
    }

    public String getFile(){
        return fileName;
    }

    public RepoFiles(Class<T> type, String fileName){
        this.type = type;
        this.fileName = fileName;
        fh = new FileHandlerUtil<>(this.type, this.fileName);
    }

    @Override
    public void add(T obj){
        try{
            fh.addObject(obj);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(T obj) {
        try{
            fh.updateObject(obj);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T get(Integer id) {
        try{
            fh.getObject(id);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try{
            fh.removeObject(id);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }    }

    @Override
    public List<T> getAll() {
        try{
            return fh.getAllObjects();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    
}