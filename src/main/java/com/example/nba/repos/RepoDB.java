package com.example.nba.repos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;
import com.example.nba.util.DBUtil;

public class RepoDB<T extends Entity> implements Repo<T> {

    private final DBUtil<T> dbUtil;
    private final Class<T> type;
    private final Connection conn;
    private final String table;

    public RepoDB(Connection conn, Class<T> type, String table){
        this.conn = conn;
        this.type = type;
        this.table = table;
        this.dbUtil = new DBUtil<>(this.conn, this.type, this.table);
    }
 

    @Override
    public void add(T obj) {
        
        try{
            dbUtil.addObject(obj);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }

    @Override
    public void update(T obj) {
        
        try{
            dbUtil.updateObject(obj);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }   
    }

    @Override
    public T get(Integer id) {
        try{
            dbUtil.getObject(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;   
    }

    @Override
    public void delete(Integer id) {
        try{
            dbUtil.removeObject(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }    
    }

    @Override
    public List<T> getAll() {
        try{
            return dbUtil.getAllObjects();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }    
        return new ArrayList<>();
    }
    
}
