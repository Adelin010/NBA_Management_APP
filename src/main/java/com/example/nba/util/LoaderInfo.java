package com.example.nba.util;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Statement;

import com.example.nba.interfaces.Entity;
import com.example.nba.interfaces.Repo;

import java.sql.ResultSet;

public class LoaderInfo<T extends Entity> {

    private final Connection conn;
    private final Repo<T> repo;
    private final String table;
    private final Class<T> type;

    public LoaderInfo(Connection conn, Repo<T> repo, Class<T> type, String table){
        this.conn = conn;
        this.repo = repo;
        this.type = type;
        this.table = table;
    }

    
    private void load()throws Exception{
 
        Statement st = conn.createStatement();
        Constructor<T> constr = type.getDeclaredConstructor(String[].class);
        ResultSet res = st.executeQuery("select * from %s".formatted(table));
        int length = res.getMetaData().getColumnCount();
        while(res.next()){  
            String[] args = new String[length];
            for(int i = 0; i < length; ++i){
                args[i] = res.getString(i+1) == null ? "null"  : res.getString(i+1);
            } 
            for(String arg: args)
                System.out.println(arg);
            T obj = constr.newInstance((Object)args);
            System.out.println(obj);
            repo.add(obj);
        }       
       
    }

    public void init()throws Exception{
        load();
    }
}
