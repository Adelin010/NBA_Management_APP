package com.example.nba.repo;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.nba.error.DBCredentialsException;
import com.example.nba.interfaces.FileBounded;
import com.example.nba.interfaces.IdBounded;
import com.example.nba.interfaces.StreamedValues;

public class RepoDB<T extends IdBounded & FileBounded & StreamedValues> implements Repo<T>{

    private static String connectionURL = null;
    private static Connection connection = null;
    private final Class<T> type;
    private Constructor<T> constr = null;
    private final String table;

    public RepoDB(String url, Class<T> type, String table)throws DBCredentialsException, Exception{
        //Later check for the jdbc:<db>: format with a regex
        connectionURL = connectionURL == null ? url : connectionURL;
        this.type = type;

        try{
            constr = this.type.getDeclaredConstructor(String[].class);
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }
        this.table = table;
        if(connection == null & connectionURL != null){
            try{
                connection =  DriverManager.getConnection(connectionURL);

            }catch(SQLException exp){
                System.err.println(exp.getMessage());
                exp.printStackTrace();
            }
        }
    }

    @Override
    public void add(T obj) {
        try{
            String q = "insert into %s values %s".formatted(table, obj.valuesof());
            Statement s = connection.createStatement();
            s.executeUpdate(q);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * The update method compare fields one by one and executes a series of updates per changed column
     * The method uses the Table provided to get the total number of columns
     * iterates through them and and updates the changes
     * 
     * !!What is chenged 
     * We compute the fileFormat() of the object in the db and the new object 
     * the String that results are all the attributes, thus comapring 2 by 2 we get the changes
     */
    @Override
    public void update(T obj) {
        T old = this.get(obj.getId());
        String[] oldArrg = old.fileFormat().split(","); 
        String[] objArrg = obj.fileFormat().split(",");
        try{
            Statement st = connection.createStatement();
            String query = "update %s set %s = '%s' where id = %d";
            String query2 = "update %s set %s = %d where id = %d";
            String query3 = "update %s set %s = %.2f where id = %d";
            int id = Integer.parseInt(objArrg[0]);
            String[] cols = this.getColNames();
            connection.setAutoCommit(false);
            for(int i = 1; i < cols.length; ++i){
                if(!oldArrg[i].equals(objArrg[i])){
                    //create the update statement 
                    int kindOfColumn = this.kindOfText(objArrg[i]);
                    System.err.println(kindOfColumn);
                    if(kindOfColumn == 2){
                        //varchar column case
                        String pre = query.formatted(table, cols[i], objArrg[i], id);
                        System.err.println(pre);
                        st.addBatch(pre);
                        //set the new value

                    }else if(kindOfColumn == 1){
                        //Numeric column case
                        String pre = query3.formatted(table, cols[i], Double.parseDouble(objArrg[i]), id);
                        System.err.println(pre);
                        st.addBatch(pre);
                        //set the new value
                    }else if (kindOfColumn == 0){
                        //varchar column case
                        String pre = query2.formatted(table, cols[i], Integer.parseInt(objArrg[i]), id);
                        System.err.println(pre);
                        st.addBatch(pre);
                        //set the new value
                    }                        
                }
            }
            st.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);

        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    

    }

    @Override
    public T get(Integer id) {

        try{
            String query = "select * from %s where id = %d".formatted(table, id);
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery(query);
            /* Generate the object based on the table row */
            return instantiate(result).get(0);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;  
    }

    @Override
    public void delete(Integer id) {
        try{
            String q = "delete from %s where id = %d".formatted(table, id);
            Statement s = connection.createStatement();
            s.executeUpdate(q);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        try{
            String q = "select * from %s".formatted(table);
            Statement s = connection.createStatement();
            ResultSet res = s.executeQuery(q);
            return instantiate(res);

        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        if(connection != null)
            try{    
                connection.close();
                connection = null;
                connectionURL = null;
            }catch(SQLException exp){
                System.out.println(exp.getMessage());
                exp.printStackTrace();
            }
    }

    /*
     * Added functions for extended functionality
     */
    public <G> List<T> getBySpecificValue(String column, G value){
        try{
            //Check the existance of the column
            if(!checkColumnExistance(column))
                return null;
            
            String q = "select * from %s".formatted(table);
            if(value instanceof Integer)
                q = "select * from %s where %s = %d".formatted(table, column, value);
            else if(value instanceof String)
                q = "select * from %s where %s like '%s'".formatted(table, column, value);

            Statement s = connection.createStatement();
            var res = s.executeQuery(q);
            return instantiate(res);

        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public <G> void deleteBy(String col, G val){
        try{
            if(!checkColumnExistance(col))
                return;

            String q = "delete from %s".formatted(table);
            if(val instanceof Integer)
                q = "delete from %s where %s = %d".formatted(table, col, val);
            else if (val instanceof String)
                q = "delete from %s where %s like '%s'".formatted(table, col, val);
            Statement s = connection.createStatement();
            s.executeUpdate(q);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /*
     * Private methods for easy of use
     */
    private List<T> instantiate(ResultSet set)throws Exception{
        if(set == null)
            return null;
        int len = set.getMetaData().getColumnCount();
        if(len ==  0)
            return null;
        List<T> list = new ArrayList<>();
        String[] arr = new String[len];
        while(set.next()){
            for(int i = 0; i < len; ++i){
                if(set.getString(i+1) == null)
                    arr[i] = "null";
                else 
                    arr[i] = set.getString(i+1);
            }
            T inst = constr.newInstance((Object)arr);
            list.add(inst);
        }
        return list.size() != 0 ? list : null;
    }

    private boolean checkColumnExistance(String column)throws Exception{
        String q = """
                select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS 
                where TABLE_NAME like '%s' and COLUMN_NAME like '%s' 
                """.formatted(table, column);
        Statement s = connection.createStatement();
        var res = s.executeQuery(q);
        return res.next();
    }

    private String[] getColNames() throws Exception{
        ResultSetMetaData meta = connection.createStatement().executeQuery("select top 1 * from %s".formatted(table)).getMetaData();
        String[] colNames = new String[meta.getColumnCount()];
        for(int i = 0; i < colNames.length; ++i){
            colNames[i] = meta.getColumnName(i+1);
        }
        return colNames;
    }

    /**
     * 
     * @param s
     * @return an int that represents what kind of text the String is 
     * if ret = 0 it is a INT
     * if ret = 1 it is a DOUBLE
     * if ret = 2 it is a Regular text
     */
    private short kindOfText(String s){ 
        if(s.matches("^[-]?[0-9]+$"))
            return 0;
        else if (s.matches("^[-]?[0-9]+\\.[0-9]+$"))
            return 1;
        else 
            return 2;
    }
    
}
