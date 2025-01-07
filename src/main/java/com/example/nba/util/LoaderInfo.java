package com.example.nba.util;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Statement;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;
import com.example.nba.repos.RepoDB;

import java.sql.ResultSet;

public class LoaderInfo<T extends Entity> {

    private final Connection conn;
    private final Repo<T> repo;
    private final String table;
    private final Class<T> type;
    private final RepoDB<T> repodb;

    public LoaderInfo(Connection conn, Repo<T> repo, Class<T> type, String table){
        this.conn = conn;
        this.repo = repo;
        this.type = type;
        this.table = table;
        this.repodb = new RepoDB<>(conn, type, table);
    }

    
    private void upload()throws Exception{
 
        Statement st = conn.createStatement();
        Constructor<T> constr = type.getDeclaredConstructor(String[].class);
        ResultSet res = st.executeQuery("select * from %s".formatted(table));
        int length = res.getMetaData().getColumnCount();
        while(res.next()){  
            String[] args = new String[length];
            for(int i = 0; i < length; ++i){
                args[i] = res.getString(i+1) == null ? "null"  : res.getString(i+1);
            } 
            
            T obj = constr.newInstance((Object)args);
            repo.add(obj);
        }       
       
    }

    private int binarySearch(Integer id, Integer[] values){
        int left = 0, right = values.length;

        while(left <= right){
            int middle = (left + (right - left)) / 2;
            if(values[middle] ==  id)
                return middle;
            if(values[middle] < id){
                left = middle + 1;
            }else{
                right = middle - 1;
            }
        }
        return -1;
    }

    private void download()throws Exception{
        //delete all the table info
        // conn.createStatement().executeQuery("delete from %s".formatted(table));
        //for each entry in the repo add the instance to the table
        for(var elem: repo.getAll()){
            if(repodb.get(elem.getId()) == null)
                repodb.add(elem);
            else 
                repodb.update(elem);
           
        }

        for(var elem: repodb.getAll()){
            if(repo.get(elem.getId()) == null)
                repodb.delete(elem.getId());
        }
        
    }

    public void init()throws Exception{
        upload();
    }
    public void flushBack() throws Exception{
        download();
    }

}
