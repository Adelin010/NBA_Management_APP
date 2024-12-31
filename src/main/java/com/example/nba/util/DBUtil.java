package com.example.nba.util;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.nba.interfaces.Entity;

public class DBUtil<T extends Entity> {
    private final Connection conn;
    private final Class<T> type;
    private final String table;

    public DBUtil(Connection conn, Class<T> type, String table){
        this.conn = conn;
        this.type = type;
        this.table = table;
    }

    public void addObject(T obj)throws SQLException{
        Statement st = conn.createStatement();
        String[] values = obj.seq(true).split(",");
        for(int i = 0; i < values.length; ++i){
            if(kindOfText(values[i]) == 2){
                values[i] = "'%s'".formatted(values[i]);
            }
        }
        String query = "insert into %s values(%s)".formatted(table, String.join(",", values));
        System.out.println(query);
        st.execute(query);
    }

    public T getObject(Integer id)throws SQLException, Exception{
        Statement st = conn.createStatement();
        String query = "select * from %s where id = %d".formatted(table, id);
        ResultSet res = st.executeQuery(query);
        return instatiate(res).get(0);
    }

    public void updateObject(T obj)throws SQLException, Exception{
        Statement st = conn.createStatement();
        PreparedStatement pst = null;
        //get the column names
        String query = "select top 1 * from %s".formatted(table);
        ResultSet res = st.executeQuery(query);
        int colLen = res.getMetaData().getColumnCount();
        System.out.println(colLen);
        String[] columns = new String[colLen];
        for(int i = 0; i < colLen; ++i){
            columns[i] = res.getMetaData().getColumnName(i+1);
            System.out.println(columns[i]);
        }

        //Get for each column the value and compare it to the new object 
        String[] oldVal = getObject(obj.getId()).seq(false).split(",");
        String[] newVal = obj.seq(false).split(",");
        for(int i = 0; i < colLen; ++i){
            System.out.println(newVal[i] + " " + oldVal[i]);
            if(!newVal[i].equals(oldVal[i])){
                query = "update %s set %s = ? where id = %d".formatted(table, columns[i], obj.getId());
                pst = conn.prepareStatement(query);
                int typeText = kindOfText(newVal[i]);
                if(typeText == 0){
                    pst.setInt(1, Integer.parseInt(newVal[i]));
                }else if (typeText == 1){
                    pst.setDouble(1, Double.parseDouble(newVal[i]));
                }else{
                    pst.setString(1, newVal[i]);
                }
                pst.execute();
            }
        }

        
    }

    public void removeObject(Integer id)throws SQLException{
        String query = "delete from %s where id = %d".formatted(table, id);
        Statement st = conn.createStatement();
        st.execute(query);
    }

    public List<T> getAllObjects() throws Exception{
        String query = "select * from %s".formatted(table);
        var res = conn.createStatement().executeQuery(query);
        return instatiate(res);
    }


    
    public <G> List<T> getByColumn(String column, G val)throws SQLException, Exception{ 
        String query;
        if(val instanceof String){
            query = "select * from %s where %s like '%s'".formatted(table, column, val);
        }else if(val instanceof Double){
            query = "select * from %s where %s = %.2f".formatted(table, column, val);
        }else{
            query = "select * from %s where %s = %d".formatted(table, column, val);
        }
        var res = conn.createStatement().executeQuery(query);    
        return instatiate(res);    
    }

    public <G> List<T> getByRange(String column, G start, G end)throws SQLException, Exception{
        String query = "";
        if(start instanceof Integer){
            query = "select * from %s where %s between %d and %d".formatted(table, column, start, end);
        }else if(start instanceof Double){
            query = "select * from %s where %s between %.2f and %.2f".formatted(table, column, start, end);
        }
        var res = conn.createStatement().executeQuery(query);
        return instatiate(res);
    } 

    public List<T> sortByColumn(String column, boolean desc)throws SQLException, Exception{
        String q = "select * from %s order by %s ".formatted(table, column);
        q += desc ? "ASC" : "DESC";
        var res = conn.createStatement().executeQuery(q);
        return instatiate(res);
    }

    public List<T> getByLogicalOr(String[] cond)throws SQLException, Exception{
        String query = "select * from %s where ".formatted(table);
        for(int i = 0; i < cond.length; ++i){
            if(i != 0)
                query += " or ";
            query += cond[i];
        }
        System.out.println(query);
        var res = conn.createStatement().executeQuery(query);    
        return instatiate(res);    
    }

    public List<T> getByLogicalAnd(String[] cond)throws SQLException, Exception{
        String query = "select * from %s where ".formatted(table);
        for(int i = 0; i < cond.length; ++i){
            if(i != 0)
                query += " and ";
            query += cond[i];
        }
        System.out.println(query);
        var res = conn.createStatement().executeQuery(query);    
        return instatiate(res);    
    }


    private short kindOfText(String s){ 
        if(s.matches("^[-]?[0-9]+$"))
            return 0;
        else if (s.matches("^[-]?[0-9]+\\.[0-9]+$"))
            return 1;
        else 
            return 2;
    }

    public List<T> instatiate(ResultSet r)throws SQLException, Exception{
        ArrayList<T> list = new ArrayList<>();
        Constructor<T> constr = type.getDeclaredConstructor(String[].class);  
        int length = r.getMetaData().getColumnCount();
        String[] values = new String[length];
        while(r.next()){
            for(int i = 0; i < length; ++i){
                String arg = r.getString(i+1);
                values[i] = arg == null ? "null" : arg;
            }
            list.add(constr.newInstance((Object)values));
        }
        return list;
    }

    

    
}
