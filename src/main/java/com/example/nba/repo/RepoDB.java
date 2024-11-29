package com.example.nba.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.example.nba.error.DBCredentialsException;
import com.example.nba.interfaces.FileBounded;
import com.example.nba.interfaces.IdBounded;

public class RepoDB<T extends IdBounded & FileBounded> implements Repo<T>{

    private final String[] connectionInfos;
    private static Connection connection = null;

    public RepoDB(String[] args)throws DBCredentialsException{
        if(args.length != 3)
            throw new DBCredentialsException("Number of credential infos insufucient. There must be 3 arguments: url, username, password");

        //Register the Driver

        connectionInfos = new String[3];
        connectionInfos[0] = args[0];
        connectionInfos[1] = args[1];
        connectionInfos[2] = args[2];

        if(connection == null){
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection =  DriverManager.getConnection(connectionInfos[0], connectionInfos[1], connectionInfos[2]);
                Statement st = connection.createStatement();
                ResultSet res = st.executeQuery("select * from TCheck");
                System.out.println(res);

            }catch(SQLException exp){
                System.out.println(exp.getMessage());
                exp.printStackTrace();
            }catch(ClassNotFoundException exp){
                System.out.println(exp.getMessage());
                exp.printStackTrace();
            }
        }
    }

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

    public void close(){
        if(connection != null)
            try{
                connection.close();

            }catch(SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
    }
    
}
